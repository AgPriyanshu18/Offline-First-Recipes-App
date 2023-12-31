package com.example.foodiefolio.util

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import timber.log.Timber

inline fun <ResultType, RequestType> networkBoundResource(
    // responsible for getting data from database
    crossinline query: () -> Flow<ResultType>,
    // responsible for fetching new data from rest api
    crossinline fetch: suspend () -> RequestType,
    // responsible for taking data from fetch and saving it to database
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    // responsible for deciding whether to fetch new data from rest api
    crossinline shouldFetch: (ResultType) -> Boolean = { true },
//    // responsible for handling error when fetching data from rest api
    crossinline onFetchFailed: (Throwable) -> Unit = { Timber.e(it) }
) = flow {

    // get one list of product from database
    val data = query().first()
    //if its time to update cache if data is decent or not
    val flow = if (shouldFetch(data)) {

        // loading and cache data
        emit(Resource.Loading(data))

        val fetchedResult = fetch()

        Log.e("networkBoundResource",fetchedResult.toString())
        // if data is not same as api data
        if (data != fetchedResult) {
            try {
                // save new data to database
                saveFetchResult(fetchedResult)
                Log.e("networkBoundResource pass",fetchedResult.toString())

                // new data from api
                query().map {
                    Log.e("networkBoundResource success",it.toString())
                    Resource.Success(it)
                }
            } catch (t: Throwable) {
                Log.e("networkBoundResource failed",t.toString())


                // handle error
                onFetchFailed(t)
                // error and cache data
                query().map { Resource.Error(t, it) }
            }
        } else { // same data from api
            // cache data
            query().map { Resource.Success(it) }
        }
    } else {
        // cache data
        query().map { Resource.Success(it) }
    }
    // get all
    emitAll(flow)
}
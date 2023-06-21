package com.example.foodiefolio.data.repository

import android.util.Log
import androidx.room.withTransaction
import com.example.foodiefolio.data.api.FoodieAPI
import com.example.foodiefolio.data.database.MealDatabase
import com.example.foodiefolio.data.model.Category
import com.example.foodiefolio.data.model.MealDetails
import com.example.foodiefolio.data.model.Meals
import com.example.foodiefolio.util.Resource
import com.example.foodiefolio.util.networkBoundResource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class MealRepositoryImpl(
    private val api: FoodieAPI,
    private val db: MealDatabase
) : MealRepository{

    private val dao = db.mealDao()
    private val TAG = "mealrepIMPLOL"

    override fun getCategories(): Flow<Resource<List<Category>>> = networkBoundResource(
        query = {
            dao.getCategories()
        },
        fetch = {
            delay(1000)
            try {
                val p = api.getCategories()
                p
            }catch (e  :Exception){
                e.printStackTrace()
                null
            }
        },
        saveFetchResult = { categories ->
            db.withTransaction {
                if (categories != null) {
                    dao.insertAllCategories(categories.categories)
                }
            }
        }
    )

    override fun getMealDetails(id: String): Flow<Resource<MealDetails>> = networkBoundResource(
        query = {
            dao.getMealDetails(id.toInt())
        },
        fetch = {
            delay(500)
            api.getMealByID(id)
        },
        saveFetchResult = { mealDetails ->
            db.withTransaction {
                dao.insertMealDetails(mealDetails.meals[0])
            }
        }
    )

    override fun getMeal(cat: String): Flow<Resource<List<Meals>>> = networkBoundResource(
        query = {
            dao.getMeal(cat)
        },
        fetch = {
            delay(1000)
            try {
                api.getMealByCategory(cat)
            }catch (e : Exception){
                e.printStackTrace()
                null
            }
        },
        saveFetchResult = { meals ->
            db.withTransaction {
                meals?.meals?.forEach {
                    it.category = cat
                }
                Log.d(TAG, "getMeal: ${meals?.meals}")
                dao.insertAllMeal(meals!!.meals)
            }
        }
    )

    override fun getSearchResults(query: String): Flow<Resource<List<Meals>>> {

        return flow {
            emit(Resource.Loading())
            try {
                val response = api.getSearch(query)
                if (response.meals.isNotEmpty()) {
                    emit(Resource.Success(response.meals))
                }
            } catch (e: Exception) {
                emit(Resource.Error(Throwable(e.message.toString())))
                e.printStackTrace()
            }
        }
    }

}
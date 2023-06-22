package com.example.foodiefolio.data.repository

import com.example.foodiefolio.data.model.Category
import com.example.foodiefolio.data.model.Favourite
import com.example.foodiefolio.data.model.MealDetails
import com.example.foodiefolio.data.model.Meals
import com.example.foodiefolio.util.Resource
import kotlinx.coroutines.flow.Flow

interface MealRepository {

    fun getCategories(): Flow<Resource<List<Category>>>

    fun getMealDetails(id: String): Flow<Resource<MealDetails>>

    fun getMeal(cat : String): Flow<Resource<List<Meals>>>

    fun getSearchResults(query: String) : Flow<Resource<List<Meals>>>

    fun getRandom(): Flow<Resource<MealDetails>>

    fun getFav(): Flow<Resource<List<Favourite>>>

    suspend fun insertfav(favmeal : Favourite)

    suspend fun deleteFav(id : Int)

}
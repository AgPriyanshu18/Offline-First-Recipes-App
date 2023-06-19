package com.example.foodiefolio.data.repository

import com.example.foodiefolio.data.model.Category
import com.example.foodiefolio.data.model.MealDetails
import com.example.foodiefolio.data.model.Meals
import com.example.foodiefolio.util.Resource
import kotlinx.coroutines.flow.Flow

interface MealRepository {

    fun getCategories(): Flow<Resource<List<Category>>>

    fun getMealDetails(id: Int): Flow<Resource<MealDetails>>

    fun getMeal(cat : String): Flow<Resource<List<Meals>>>

}
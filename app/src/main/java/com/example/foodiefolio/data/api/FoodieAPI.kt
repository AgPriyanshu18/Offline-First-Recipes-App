package com.example.foodiefolio.data.api

import com.example.foodiefolio.data.model.Category
import retrofit2.http.GET

interface FoodieAPI {
    companion object {
        const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"
    }

    @GET("categories.php")
    suspend fun getCategories(): List<Category>

    @GET("random.php")
    suspend fun getRandom(): List<Category>


}
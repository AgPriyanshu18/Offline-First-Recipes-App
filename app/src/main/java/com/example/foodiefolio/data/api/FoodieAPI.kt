package com.example.foodiefolio.data.api

import com.example.foodiefolio.data.model.Category
import com.example.foodiefolio.data.model.MealDetails
import com.example.foodiefolio.data.model.Meals
import retrofit2.http.GET
import retrofit2.http.Path

interface FoodieAPI {
    companion object {
        const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"
    }

    @GET("/categories.php")
    suspend fun getCategories(): List<Category>

    @GET("/random.php")
    suspend fun getRandom(): List<MealDetails>

    @GET("/lookup.php?i={id}")
    suspend fun getMealByID(@Path("id") id: Int): MealDetails

    @GET("/filter.php?c={category}")
    suspend fun getMealByCategory(@Path("category") category: String): List<Meals>

    @GET("/search.php?s={name}")
    suspend fun getMealByName(@Path("name") name: String): List<Meals>

}
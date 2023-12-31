package com.example.foodiefolio.data.api

import com.example.foodiefolio.data.model.Category
import com.example.foodiefolio.data.model.MealDetailResponse
import com.example.foodiefolio.data.model.MealDetails
import com.example.foodiefolio.data.model.Meals
import com.example.foodiefolio.data.model.categoryResponse
import com.example.foodiefolio.data.model.mealResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FoodieAPI {
    companion object {
        const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"
    }

    @GET("categories.php")
    suspend fun getCategories(): categoryResponse

    @GET("random.php")
    suspend fun getRandom(): MealDetailResponse

    @GET("lookup.php")
    suspend fun getMealByID(@Query("i") id: String): MealDetailResponse

    @GET("filter.php")
    suspend fun getMealByCategory(@Query("c") category: String): mealResponse

    @GET("filter.php")
    suspend fun getSearch(@Query("i") query: String): mealResponse

}
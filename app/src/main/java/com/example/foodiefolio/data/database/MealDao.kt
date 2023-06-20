package com.example.foodiefolio.data.database

import androidx.room.*
import com.example.foodiefolio.data.model.Category
import com.example.foodiefolio.data.model.MealDetails
import com.example.foodiefolio.data.model.Meals
import com.example.foodiefolio.util.Resource
import kotlinx.coroutines.flow.Flow

@Dao
interface MealDao {

    @Query("SELECT * FROM categories")
    fun getCategories(): Flow<List<Category>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCategories(categories: List<Category>)

    @Query("DELETE FROM categories")
    suspend fun deleteAllCategories()


    @Query("SELECT * FROM mealDetails WHERE id = :id")
    fun getMealDetails(id: Int): Flow<MealDetails>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMealDetails(meal: MealDetails)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMeal(meals: List<Meals>)

    @Query("SELECT * FROM meals")
    fun getMeal(): Flow<List<Meals>>

    @Query("DELETE FROM meals")
    suspend fun deleteAllMeals()

}
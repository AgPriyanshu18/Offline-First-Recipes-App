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

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllCategories(categories: List<Category>)


    @Query("SELECT * FROM mealDetails WHERE id = :id")
    fun getMealDetails(id: Int): Flow<MealDetails>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMealDetails(mealDetails: MealDetails)


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllMeal(meals: List<Meals>)

    @Query("SELECT * FROM meals WHERE category = :cat")
    fun getMeal(cat : String): Flow<List<Meals>>

}
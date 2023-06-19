package com.example.foodiefolio.data.database

import androidx.room.*
import com.example.foodiefolio.data.model.Category
import com.example.foodiefolio.data.model.MealDetails
import com.example.foodiefolio.util.Resource
import kotlinx.coroutines.flow.Flow

@Dao
interface MealDao {

    @Query("SELECT * FROM categories")
    suspend fun getCategories(): Flow<List<Category>>

    @Query("SELECT * FROM categories WHERE id = :id")
    fun getMeal(id: Int): Flow<MealDetails>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCategories(categories: List<Category>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMealDetails(meal: MealDetails)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMealDetails(meals: List<MealDetails>)

    @Query("DELETE FROM categories")
    suspend fun deleteAllMeals(): Resource<Unit>

    @Query("DELETE FROM categories")
    suspend fun deleteAllCategories()

}
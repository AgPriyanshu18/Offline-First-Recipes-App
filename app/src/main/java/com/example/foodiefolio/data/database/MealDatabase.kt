package com.example.foodiefolio.data.database

import androidx.room.Database
import androidx.room.Entity
import androidx.room.RoomDatabase
import com.example.foodiefolio.data.model.Category
import com.example.foodiefolio.data.model.Favourite
import com.example.foodiefolio.data.model.MealDetails
import com.example.foodiefolio.data.model.Meals

@Database(entities = [Meals::class, Category::class,MealDetails::class, Favourite::class], version = 1, exportSchema = false)
abstract class MealDatabase : RoomDatabase(){
    abstract fun mealDao() : MealDao
}
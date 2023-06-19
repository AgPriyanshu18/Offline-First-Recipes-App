package com.example.foodiefolio.data.repository

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

class MealRepositoryImpl(
    private val api: FoodieAPI,
    private val db: MealDatabase
) : MealRepository{

    private val dao = db.mealDao()

    override fun getCategories(): Flow<Resource<List<Category>>> = networkBoundResource(
        query = {
            dao.getCategories()
        },
        fetch = {
            delay(1000)
            api.getCategories()
        },
        saveFetchResult = { categories ->
            db.withTransaction {
                dao.deleteAllCategories()
                dao.insertAllCategories(categories)
            }
        }
    )

    override fun getMealDetails(id: Int): Flow<Resource<MealDetails>> = networkBoundResource(
        query = {
            dao.getMealDetails(id)
        },
        fetch = {
            delay(500)
            api.getMealByID(id)
        },
        saveFetchResult = { mealDetails ->
            db.withTransaction {
                dao.insertMealDetails(mealDetails)
            }
        }
    )

    override fun getMeal(cat: String): Flow<Resource<List<Meals>>> = networkBoundResource(
        query = {
            dao.getMeal()
        },
        fetch = {
            delay(1000)
            api.getMealByCategory(cat)
        },
        saveFetchResult = { meals ->
            db.withTransaction {
                dao.deleteAllMeals()
                dao.insertAllMeal(meals)
            }
        }
    )

}
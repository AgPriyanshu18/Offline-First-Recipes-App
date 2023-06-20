package com.example.foodiefolio.di

import android.app.Application
import androidx.room.Room
import com.example.foodiefolio.data.api.FoodieAPI
import com.example.foodiefolio.data.database.MealDatabase
import com.example.foodiefolio.data.repository.MealRepository
import com.example.foodiefolio.data.repository.MealRepositoryImpl
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Singleton
    @Provides
    fun provideMealAPI(): FoodieAPI =
        Retrofit.Builder()
            .baseUrl(FoodieAPI.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(FoodieAPI::class.java)

    @Provides
    fun provideMealRepository(api: FoodieAPI,db:MealDatabase): MealRepository = MealRepositoryImpl(api,db)

    @Singleton
    @Provides
    fun provideDatabase(app: Application) =
        Room.databaseBuilder(app, MealDatabase::class.java, "meal_database")
            .addMigrations()
            .build()

}
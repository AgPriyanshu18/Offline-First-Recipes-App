package com.example.foodiefolio.ui.random

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import com.example.foodiefolio.data.repository.MealRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RandomViewModel @Inject constructor(
    private val repo : MealRepository
) : ViewModel(){

    private var recipeData = MutableLiveData(Unit)

    private val recipes = recipeData.switchMap {
        repo.getRandom().asLiveData()
    }

    val recipe = recipes

    fun getData(){
        recipeData.value = Unit
    }

}
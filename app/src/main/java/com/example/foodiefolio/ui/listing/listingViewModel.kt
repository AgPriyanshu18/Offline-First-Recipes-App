package com.example.foodiefolio.ui.listing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import com.example.foodiefolio.data.model.Meals
import com.example.foodiefolio.data.repository.MealRepository
import com.example.foodiefolio.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class listingViewModel @Inject constructor(
    private val repo: MealRepository
) : ViewModel(){
    private var recipeData = MutableLiveData<String>()

    private val recipes = recipeData.switchMap {
        repo.getMeal(it).asLiveData()
    }

    val recipe : LiveData<Resource<List<Meals>>> = recipes

    fun getData(cat : String){
        recipeData.value = cat
    }
}
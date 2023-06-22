package com.example.foodiefolio.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import com.example.foodiefolio.data.model.MealDetails
import com.example.foodiefolio.data.repository.MealRepository
import com.example.foodiefolio.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repo: MealRepository
) : ViewModel(){
    private var mealDetailData = MutableLiveData<String>()

    private val mealDetails = mealDetailData.switchMap {
        repo.getMealDetails(it).asLiveData()
    }

    val mealDetail : LiveData<Resource<MealDetails>> = mealDetails

    fun getData(it : String){
        mealDetailData.value = it
    }
}
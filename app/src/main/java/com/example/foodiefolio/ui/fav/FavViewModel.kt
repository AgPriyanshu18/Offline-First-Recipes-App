package com.example.foodiefolio.ui.fav

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import com.example.foodiefolio.data.repository.MealRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavViewModel @Inject constructor(
    private val repo: MealRepository
) : ViewModel() {

    private var favData = MutableLiveData(Unit)

    private val fav = favData.switchMap {
        repo.getFav().asLiveData()
    }

    val favList = fav

    fun getData(){
        favData.value = Unit
    }

}
package com.example.foodiefolio.ui.categories

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import com.example.foodiefolio.data.repository.MealRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class categoryViewModel @Inject constructor(
    private val repo: MealRepository
) : ViewModel() {

        private val catData = MutableLiveData(Unit)

    val categories = catData.switchMap {
            repo.getCategories().asLiveData()
        }

    fun refresh() {
        catData.value = Unit
    }

}
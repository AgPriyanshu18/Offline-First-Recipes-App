package com.example.foodiefolio.ui.search

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
class SearchViewModel @Inject constructor(private var SearchRepo: MealRepository) : ViewModel(){

    private var searchData = MutableLiveData<String>()

    private val search = searchData.switchMap {
        SearchRepo.getSearchResults(it).asLiveData()
    }

    val searchValue : LiveData<Resource<List<Meals>>> = search

    fun getData(cat : String){
        searchData.value = cat
    }

}
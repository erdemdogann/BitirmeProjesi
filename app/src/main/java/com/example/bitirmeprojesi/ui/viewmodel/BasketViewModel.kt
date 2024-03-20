package com.example.bitirmeprojesi.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bitirmeprojesi.data.entity.Order
import com.example.bitirmeprojesi.data.repo.FoodRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(var frepo: FoodRepo) : ViewModel() {
    var foodList = MutableLiveData<List<Order>>()

    init {
        basket("erdem")
    }

    fun basket(user_name: String) {
        viewModelScope.launch {
            try {
                foodList.value = frepo.basket(user_name)
            } catch (e: Exception) { }
        }
    }

    fun delete(user_name: String, food_id: Int) {
        viewModelScope.launch {
            frepo.delete(user_name, food_id)
            basket(user_name)
        }
    }

}
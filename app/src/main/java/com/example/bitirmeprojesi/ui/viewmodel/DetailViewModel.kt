package com.example.bitirmeprojesi.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bitirmeprojesi.data.repo.FoodRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(var frepo: FoodRepo) : ViewModel() {

    fun order(
        food_name: String,
        food_image: String,
        food_price: Int,
        food_order: Int,
        user_name: String
    ) {
        viewModelScope.launch {
            frepo.order(food_name, food_image, food_price, food_order, user_name)
        }
    }
}
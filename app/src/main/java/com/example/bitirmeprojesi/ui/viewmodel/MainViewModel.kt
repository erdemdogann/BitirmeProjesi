package com.example.bitirmeprojesi.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bitirmeprojesi.data.entity.Yemekler
import com.example.bitirmeprojesi.data.repo.FoodRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(var frepo: FoodRepo) : ViewModel() {
    var foodList = MutableLiveData<List<Yemekler>>()

    init {
        load()
    }

    fun load (){
        CoroutineScope(Dispatchers.Main).launch {
            foodList.value = frepo.allFood()
        }
    }
}
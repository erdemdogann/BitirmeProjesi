package com.example.bitirmeprojesi.data.datasource

import com.example.bitirmeprojesi.data.entity.Yemekler
import com.example.bitirmeprojesi.retrofit.FoodDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FoodDataSource(var fdao: FoodDao) {
    suspend fun allFood(): List<Yemekler> =
        withContext(Dispatchers.IO) {
            return@withContext fdao.allFood().yemekler
        }

}
package com.example.bitirmeprojesi.data.repo

import com.example.bitirmeprojesi.data.datasource.FoodDataSource
import com.example.bitirmeprojesi.data.entity.Yemekler

class FoodRepo(var fds: FoodDataSource) {
    suspend fun allFood(): List<Yemekler> = fds.allFood()
}
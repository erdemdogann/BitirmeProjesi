package com.example.bitirmeprojesi.data.datasource

import com.example.bitirmeprojesi.data.entity.Order
import com.example.bitirmeprojesi.data.entity.Yemekler
import com.example.bitirmeprojesi.retrofit.FoodDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FoodDataSource(var fdao: FoodDao) {
    suspend fun allFood(): List<Yemekler> =
        withContext(Dispatchers.IO) {
            return@withContext fdao.allFood().yemekler
        }

    suspend fun order(
        food_name: String,
        food_image: String,
        food_price: Int,
        food_order: Int,
        user_name: String
    ) =
        fdao.order(food_name, food_image, food_price, food_order, user_name)

    suspend fun basket(user_name: String): List<Order> =
        withContext(Dispatchers.IO) {
            return@withContext fdao.basket(user_name).sepet_yemekler
        }

    suspend fun delete(user_name: String, food_id: Int) = fdao.delete(user_name, food_id)

}
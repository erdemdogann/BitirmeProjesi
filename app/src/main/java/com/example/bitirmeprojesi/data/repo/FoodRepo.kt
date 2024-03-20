package com.example.bitirmeprojesi.data.repo

import com.example.bitirmeprojesi.data.datasource.FoodDataSource
import com.example.bitirmeprojesi.data.entity.Order
import com.example.bitirmeprojesi.data.entity.Yemekler

class FoodRepo(var fds: FoodDataSource) {
    suspend fun allFood(): List<Yemekler> = fds.allFood()

    suspend fun order(
        food_name: String,
        food_image: String,
        food_price: Int,
        food_order: Int,
        user_name: String
    ) = fds.order(food_name, food_image, food_price, food_order, user_name)

    suspend fun basket(user_name: String): List<Order> = fds.basket(user_name)

    suspend fun delete(user_name: String, food_id: Int) = fds.delete(user_name, food_id)
}
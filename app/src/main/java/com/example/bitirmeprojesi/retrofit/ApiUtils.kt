package com.example.bitirmeprojesi.retrofit

import retrofit2.Retrofit

class ApiUtils {
    companion object {
        val BASE_URL = "http://kasimadalan.pe.hu/"
        fun getFoodDao(): FoodDao {
            return RetrofitClient.getClient(BASE_URL).create(FoodDao::class.java)
        }
    }
}
package com.example.bitirmeprojesi.retrofit

import com.example.bitirmeprojesi.data.entity.Foods
import retrofit2.http.GET

interface FoodDao {
    //base url = http://kasimadalan.pe.hu/

    @GET("yemekler/tumYemekleriGetir.php")
    suspend fun allFood(): Foods

}
package com.example.bitirmeprojesi.retrofit

import com.example.bitirmeprojesi.data.entity.BasketFood
import com.example.bitirmeprojesi.data.entity.CRUDAnswer
import com.example.bitirmeprojesi.data.entity.Foods
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface FoodDao {
    //yemekler/resimler/

    @GET("yemekler/tumYemekleriGetir.php")
    suspend fun allFood(): Foods

    @POST("yemekler/sepeteYemekEkle.php")
    @FormUrlEncoded
    suspend fun order(
        @Field("yemek_adi") food_name: String,
        @Field("yemek_resim_adi") food_image: String,
        @Field("yemek_fiyat") food_price: Int,
        @Field("yemek_siparis_adet") food_order: Int,
        @Field("kullanici_adi") user_name: String
    ): CRUDAnswer

    @POST("yemekler/sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    suspend fun basket(@Field("kullanici_adi") user_name: String): BasketFood

    @POST("yemekler/sepettenYemekSil.php")
    @FormUrlEncoded
    suspend fun delete(
        @Field("kullanici_adi") user_name: String,
        @Field("semet_yemek_id") food_id: Int
    ): CRUDAnswer

}
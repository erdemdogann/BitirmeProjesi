package com.example.bitirmeprojesi.data.entity

import java.io.Serializable

data class Yemekler(
    var yemek_adi: String,
    var yemek_fiyat: String,
    var yemek_id: String,
    var yemek_resim_adi: String
) : Serializable {

}
package com.example.bitirmeprojesi.data.entity

import java.io.Serializable

data class Card(
    var cardId : String? = "",
    var cardNumber: String? = "",
    var cardDate: String? = "",
    var cardCVC: String? = ""
):Serializable

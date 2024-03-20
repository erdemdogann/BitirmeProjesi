package com.example.bitirmeprojesi.data

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(foodName: String){
    var url="http://kasimadalan.pe.hu/yemekler/resimler/$foodName"
    Glide.with(this).load(url).into(this)
}
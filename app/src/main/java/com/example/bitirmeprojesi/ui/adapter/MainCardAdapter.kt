package com.example.bitirmeprojesi.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bitirmeprojesi.data.entity.Yemekler
import com.example.bitirmeprojesi.databinding.CardMainBinding
import com.example.bitirmeprojesi.ui.viewmodel.MainViewModel

class MainCardAdapter(var allFood: List<Yemekler>) :
    RecyclerView.Adapter<MainCardAdapter.Holder>() {
    inner class Holder(val itemBinding: CardMainBinding) : RecyclerView.ViewHolder(itemBinding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = CardMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return allFood.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val food = allFood.get(position)

        holder.itemBinding.foodName.text = food.yemek_adi
        holder.itemBinding.foodPrice.text = food.yemek_fiyat

    }
}
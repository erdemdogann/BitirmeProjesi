package com.example.bitirmeprojesi.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bitirmeprojesi.data.entity.Yemekler
import com.example.bitirmeprojesi.data.loadImage
import com.example.bitirmeprojesi.databinding.CardMainBinding
import com.example.bitirmeprojesi.ui.fragment.MainFragment

class MainCardAdapter :
    RecyclerView.Adapter<MainCardAdapter.Holder>() {
    var onClick: (Yemekler) -> Unit = { }
    var allFood: List<Yemekler> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class Holder(val itemBinding: CardMainBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(allFood: Yemekler) {
            itemBinding.foodName.text = allFood.yemek_adi
            itemBinding.foodPrice.text = "${allFood.yemek_fiyat} ₺"

            itemBinding.imageView.loadImage(allFood.yemek_resim_adi)
        }
        init {
            itemBinding.root.setOnClickListener {
                onClick(allFood[this.bindingAdapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = CardMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return allFood.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(allFood[position])
    }
}
package com.example.bitirmeprojesi.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bitirmeprojesi.data.entity.Order
import com.example.bitirmeprojesi.data.entity.Yemekler
import com.example.bitirmeprojesi.data.loadImage
import com.example.bitirmeprojesi.databinding.BasketCardBinding
import com.example.bitirmeprojesi.ui.viewmodel.BasketViewModel

class BasketCardAdapter :
    RecyclerView.Adapter<BasketCardAdapter.Holder>() {

    var onClick: (Int,String) -> Unit = {id,userName->}
    var basketFood: MutableList<Order> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var price = 0

    inner class Holder(val itemBinding: BasketCardBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(orders: Order) {

            itemBinding.apply {
                orderFoodName.text = orders.yemek_adi
                orderFoodNumber.text = orders.yemek_siparis_adet
                orderFoodPrice.text = "${orders.yemek_fiyat} ₺"
                price = orders.yemek_fiyat.toInt() * orders.yemek_siparis_adet.toInt()
                textView4.text = "Total: $price ₺"
                delete.setOnClickListener {
                    onClick.invoke(orders.sepet_yemek_id.toInt(),orders.kullanici_adi)
                }
                foodImage.loadImage(orders.yemek_resim_adi)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = BasketCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return basketFood.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(basketFood[position])
    }
}
package com.example.bitirmeprojesi.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bitirmeprojesi.data.entity.Order
import com.example.bitirmeprojesi.databinding.BasketCardBinding
import com.example.bitirmeprojesi.ui.viewmodel.BasketViewModel

class BasketCardAdapter(var viewModel: BasketViewModel) :
    RecyclerView.Adapter<BasketCardAdapter.Holder>() {
    var basketFood: List<Order> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class Holder(val itemBinding: BasketCardBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = BasketCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return basketFood.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val orders = basketFood.get(position)
        holder.itemBinding.orderFoodName.text = orders.yemek_adi
        holder.itemBinding.orderFoodNumber.text = orders.yemek_siparis_adet
        holder.itemBinding.orderFoodPrice.text = orders.yemek_fiyat
        val price = orders.yemek_fiyat.toInt() * orders.yemek_siparis_adet.toInt()
        holder.itemBinding.textView4.text = "Total: $price"
        holder.itemBinding.button.setOnClickListener {
            viewModel.delete(orders.kullanici_adi,orders.sepet_yemek_id.toInt())
        }
    }
}
package com.example.bitirmeprojesi.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bitirmeprojesi.data.entity.Order
import com.example.bitirmeprojesi.data.entity.Yemekler
import com.example.bitirmeprojesi.databinding.BasketCardBinding
import com.example.bitirmeprojesi.ui.viewmodel.BasketViewModel

class BasketCardAdapter(var viewModel: BasketViewModel) :
    RecyclerView.Adapter<BasketCardAdapter.Holder>() {

    var onClick: (Yemekler) -> Unit = { }
    var basketFood: MutableList<Order> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class Holder(val itemBinding: BasketCardBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(orders: Order) {

            itemBinding.apply {
                orderFoodName.text = orders.yemek_adi
                orderFoodNumber.text = orders.yemek_siparis_adet
                orderFoodPrice.text = orders.yemek_fiyat
                val price = orders.yemek_fiyat.toInt() * orders.yemek_siparis_adet.toInt()
                textView4.text = "Total: $price"
                button.setOnClickListener {
                    viewModel.delete(
                        orders.kullanici_adi,
                        orders.sepet_yemek_id.toInt(),
                        callBack = {
                            var deletedItem = basketFood.indexOfFirst {
                                it.sepet_yemek_id == orders.sepet_yemek_id
                            }
                            basketFood.removeAt(deletedItem)
                            notifyItemRemoved(deletedItem)
                        })
                }
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
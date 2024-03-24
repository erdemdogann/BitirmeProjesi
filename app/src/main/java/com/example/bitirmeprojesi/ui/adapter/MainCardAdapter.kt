package com.example.bitirmeprojesi.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bitirmeprojesi.data.entity.Yemekler
import com.example.bitirmeprojesi.data.loadImage
import com.example.bitirmeprojesi.databinding.CardMainBinding
import com.example.bitirmeprojesi.ui.fragment.MainFragment

class MainCardAdapter :
    RecyclerView.Adapter<MainCardAdapter.Holder>(), Filterable {
    var onClick: (Yemekler) -> Unit = { }

    var filterFood:List<Yemekler>? = listOf()
    var allFood: List<Yemekler> = listOf()
        set(value) {
            field = value
            filterFood = allFood
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
                filterFood?.let {
                    onClick(it[this.bindingAdapterPosition])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = CardMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return filterFood?.size ?: 0
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        filterFood?.let {
            holder.bind(it[position])
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint?.toString() ?: ""
                if (charString.isEmpty()){
                    filterFood = allFood
                }else{
                    filterFood = allFood.filter {
                        it.yemek_adi.contains(charString,true)
                    }
                }
                return FilterResults().apply { values = filterFood }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {

                filterFood = if (results?.values == null)
                    ArrayList()
                else
                    results.values as? List<Yemekler>
                notifyDataSetChanged()
            }
        }
    }
}
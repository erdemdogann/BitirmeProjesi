package com.example.bitirmeprojesi.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bitirmeprojesi.data.entity.Card
import com.example.bitirmeprojesi.databinding.PayCardBinding

class PayCardAdapter(val cardList: MutableList<Card>) : RecyclerView.Adapter<PayCardAdapter.Holder>() {

    var onClick: (String) ->Unit = {cardid ->}

    var finishClick: (Card) ->Unit = { }

    inner class Holder(val itemBinding: PayCardBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(cards: Card) {
            itemBinding.textView3.text = cards.cardNumber
            itemBinding.textView5.text = cards.cardDate
            itemBinding.textView6.text = cards.cardCVC
            itemBinding.cardId.text = cards.cardId
            itemBinding.deleteSaveCard.setOnClickListener {
                    onClick.invoke(cards.cardId!!)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = PayCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return cardList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(cardList[position])
    }
}
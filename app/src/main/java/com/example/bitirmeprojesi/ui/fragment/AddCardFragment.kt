package com.example.bitirmeprojesi.ui.fragment

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.bitirmeprojesi.R
import com.example.bitirmeprojesi.data.entity.Card
import com.example.bitirmeprojesi.databinding.FragmentAddCardBinding
import com.example.bitirmeprojesi.ui.adapter.PayCardAdapter
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class AddCardFragment : Fragment() {

    private lateinit var binding: FragmentAddCardBinding
    private var adapter: PayCardAdapter? = null

    var db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddCardBinding.inflate(inflater, container, false)

        binding.save.setOnClickListener {
            val cardNumber = binding.cardNumber.text
            val cardDate = binding.cardDate.text
            val cardCvc = binding.cardCvc.text
            val cardId = binding.cardName.text
            if (cardNumber.isNullOrEmpty().not() && cardCvc.isNullOrEmpty()
                    .not() && cardDate.isNullOrEmpty().not() && cardId.isNullOrEmpty().not()
            ) {
                val card = Card(
                    cardId.toString(),
                    cardNumber.toString(),
                    cardDate.toString(),
                    cardCvc.toString()
                )
                db.collection("Card").document(cardId.toString())
                    .set(card)
                    .addOnSuccessListener { documentReference ->
                        Log.d(ContentValues.TAG, "DocumentSnapshot added with ID: ")
                        adapter?.cardList?.add(card)
                        adapter?.notifyDataSetChanged()
                    }
                    .addOnFailureListener { e ->
                        Log.w(ContentValues.TAG, "Error adding document", e)
                    }
                findNavController().navigate(AddCardFragmentDirections.backPayment())
            }

        }

        return binding.root
    }

}
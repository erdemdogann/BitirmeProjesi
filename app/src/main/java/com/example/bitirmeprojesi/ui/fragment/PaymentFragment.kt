package com.example.bitirmeprojesi.ui.fragment

import android.content.ContentValues.TAG
import android.media.MediaPlayer.OnCompletionListener
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bitirmeprojesi.R
import com.example.bitirmeprojesi.data.entity.Card
import com.example.bitirmeprojesi.databinding.FragmentPaymentBinding
import com.example.bitirmeprojesi.ui.adapter.PayCardAdapter
import com.google.firebase.Firebase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject

class PaymentFragment : Fragment() {
    private lateinit var binding: FragmentPaymentBinding
    private lateinit var database: DatabaseReference
    private var adapter: PayCardAdapter? = null

    var db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPaymentBinding.inflate(inflater, container, false)


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
                        Log.d(TAG, "DocumentSnapshot added with ID: ")
                        adapter?.cardList?.add(card)
                        adapter?.notifyDataSetChanged()
                    }
                    .addOnFailureListener { e ->
                        Log.w(TAG, "Error adding document", e)
                    }

            }
        }
        db.collection("Card").get().addOnSuccessListener { result ->
            val cards = mutableListOf<Card>()
            for (i in result) {
                val card = i.toObject(Card::class.java)
                cards.add(card)
            }
            adapter = PayCardAdapter(cards)
            binding.cardList.adapter = adapter
            binding.cardList.layoutManager = GridLayoutManager(requireContext(), 1)
            adapter?.onClick = { id ->
                db.collection("Card").document(id).delete()
                    .addOnSuccessListener {
                        Log.d(TAG, "DocumentSnapshot successfully deleted!")
                        adapter?.cardList?.removeIf {
                            it.cardId == id
                        }
                        adapter?.notifyDataSetChanged()
                    }
                    .addOnFailureListener { e -> Log.w(TAG, "Error deleting document", e) }
            }
        }

        return binding.root
    }
}
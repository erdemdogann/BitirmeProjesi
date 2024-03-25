package com.example.bitirmeprojesi.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.bitirmeprojesi.R
import com.example.bitirmeprojesi.data.loadImage
import com.example.bitirmeprojesi.databinding.FragmentDetailBinding
import com.example.bitirmeprojesi.ui.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private lateinit var viewModel: DetailViewModel
    var number = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater, container, false)

        val bundle: DetailFragmentArgs by navArgs()
        val getFood = bundle.foods

        binding.apply {
            foodName1.text = getFood.yemek_adi
            foodPrice2.text = "${getFood.yemek_fiyat} ₺"

            add.setOnClickListener { add() }
            decrease.setOnClickListener { decrease() }
            back.setOnClickListener {
                findNavController().navigate(DetailFragmentDirections.backToMain())
            }
            imageView2.loadImage(getFood.yemek_resim_adi)

            sendBasket.setOnClickListener {
                val food_name = binding.foodName1.text.toString()
                val food_image = getFood.yemek_resim_adi
                val food_price = getFood.yemek_fiyat.toInt()
                val food_order = number
                val user_name = "erdem"
                if (food_order != 0) {
                    viewModel.order(food_name, food_image, food_price, food_order, user_name)
                }
            }

        }


        return binding.root
    }

    fun add() {
        number += 1
        binding.foodNumber.text = number.toString()
    }

    fun decrease() {
        if (number > 0) {
            number -= 1
            binding.foodNumber.text = number.toString()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: DetailViewModel by viewModels()
        viewModel = tempViewModel
    }
}
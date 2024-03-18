package com.example.bitirmeprojesi.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.bitirmeprojesi.R
import com.example.bitirmeprojesi.databinding.FragmentDetailBinding
import com.example.bitirmeprojesi.ui.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater, container, false)

        val bundle: DetailFragmentArgs by navArgs()
        val getFood = bundle.foods

        binding.foodName1.text = getFood.yemek_adi
        binding.foodPrice2.text = getFood.yemek_fiyat




        return binding.root
    }
}
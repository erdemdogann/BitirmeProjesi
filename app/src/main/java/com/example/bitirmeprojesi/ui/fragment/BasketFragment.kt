package com.example.bitirmeprojesi.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bitirmeprojesi.R
import com.example.bitirmeprojesi.databinding.FragmentBasketBinding
import com.example.bitirmeprojesi.ui.adapter.BasketCardAdapter
import com.example.bitirmeprojesi.ui.viewmodel.BasketViewModel
import com.example.bitirmeprojesi.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BasketFragment : Fragment() {
    private lateinit var binding: FragmentBasketBinding
    private lateinit var viewModel: BasketViewModel
    private var adapter: BasketCardAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBasketBinding.inflate(inflater, container, false)

        adapter = BasketCardAdapter(viewModel)
        binding.basketList.adapter = adapter
        binding.basketList.layoutManager = LinearLayoutManager(requireContext())

        viewModel.foodList.observe(viewLifecycleOwner) {
            adapter?.basketFood = it.toMutableList()
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: BasketViewModel by viewModels()
        viewModel = tempViewModel
    }
    override fun onResume() {
        super.onResume()
        viewModel.basket("erdem")
    }

}
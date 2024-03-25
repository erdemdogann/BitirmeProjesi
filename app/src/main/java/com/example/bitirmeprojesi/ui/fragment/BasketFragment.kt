package com.example.bitirmeprojesi.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
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
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBasketBinding.inflate(inflater, container, false)

        adapter = BasketCardAdapter()
        binding.basketList.adapter = adapter
        binding.basketList.layoutManager = LinearLayoutManager(requireContext())

        viewModel.foodList.observe(viewLifecycleOwner) {
            adapter?.basketFood = it.toMutableList()
            binding.totalPrice.text =
                "${requireContext().getString(R.string.total)} : ${
                    adapter?.basketFood?.sumOf { it.yemek_fiyat.toInt() * it.yemek_siparis_adet.toInt() }
                        .toString()
                } ₺"
        }
        adapter?.onClick = { id, userName ->
            viewModel.delete(
                userName,
                id,
                callBack = {
                    var deletedItem = adapter?.basketFood?.indexOfFirst {
                        it.sepet_yemek_id.toInt() == id
                    }
                    if (deletedItem != null) {
                        adapter?.basketFood?.removeAt(deletedItem)
                        adapter?.notifyItemRemoved(deletedItem)
                        binding.totalPrice.text =
                            "${requireContext().getString(R.string.total)} : ${
                                adapter?.basketFood?.sumOf { it.yemek_fiyat.toInt() * it.yemek_siparis_adet.toInt() }
                                    .toString()
                            } ₺"
                    }
                })
        }
        binding.order.setOnClickListener {
            findNavController().navigate(BasketFragmentDirections.payment())
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: BasketViewModel by viewModels()
        viewModel = tempViewModel
    }

}
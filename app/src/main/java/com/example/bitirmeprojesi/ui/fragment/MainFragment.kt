package com.example.bitirmeprojesi.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bitirmeprojesi.R
import com.example.bitirmeprojesi.databinding.FragmentMainBinding
import com.example.bitirmeprojesi.ui.adapter.MainCardAdapter
import com.example.bitirmeprojesi.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(), SearchView.OnQueryTextListener {
    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel: MainViewModel
    private var adapter: MainCardAdapter? = null
    var increase = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        binding.searchView.findViewById<SearchView>(R.id.searchView).setOnQueryTextListener(this)

        adapter = MainCardAdapter()
        binding.foodList.adapter = adapter
        adapter?.onClick = {
            findNavController().navigate(MainFragmentDirections.foodDetail(foods = it))
        }
        binding.increasing.setOnClickListener { filter() }

        viewModel.foodList.observe(viewLifecycleOwner) {
            adapter?.allFood = it
        }

        binding.foodList.layoutManager = GridLayoutManager(requireContext(),2)

        return binding.root
    }

    fun filter() {
        if (!increase) {
            viewModel.foodList.observe(viewLifecycleOwner) {
                adapter?.allFood = it.sortedBy { it.yemek_fiyat.toInt() }
            }
            increase = true
        } else {
            viewModel.foodList.observe(viewLifecycleOwner) {
                adapter?.allFood = it
            }
            increase = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: MainViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onResume() {
        super.onResume()
        viewModel.load()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        adapter?.filter?.filter(query)
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        adapter?.filter?.filter(newText)
        return false
    }
}
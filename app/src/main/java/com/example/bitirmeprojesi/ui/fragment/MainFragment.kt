package com.example.bitirmeprojesi.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bitirmeprojesi.R
import com.example.bitirmeprojesi.databinding.FragmentMainBinding
import com.example.bitirmeprojesi.ui.adapter.MainCardAdapter
import com.example.bitirmeprojesi.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        viewModel.foodList.observe(viewLifecycleOwner) {
            val mainAdapter = MainCardAdapter(it)
            binding.foodList.adapter = mainAdapter
        }
        binding.foodList.layoutManager =LinearLayoutManager(requireContext())
        return binding.root
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

}
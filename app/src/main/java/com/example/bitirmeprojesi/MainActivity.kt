package com.example.bitirmeprojesi

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.replace
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.bitirmeprojesi.data.entity.Order
import com.example.bitirmeprojesi.databinding.ActivityMainBinding
import com.example.bitirmeprojesi.ui.adapter.BasketCardAdapter
import com.example.bitirmeprojesi.ui.fragment.BasketFragment
import com.example.bitirmeprojesi.ui.fragment.MainFragment
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment

        NavigationUI.setupWithNavController(binding.bottomNavigation,navHostFragment.navController)
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.mainFragment ->{
                    navHostFragment.navController.navigate(R.id.action_global_mainFragment)
                }
                R.id.basketFragment->{
                    navHostFragment.navController.navigate(R.id.action_global_basketFragment)
                }
            }
            true
        }
    }
}
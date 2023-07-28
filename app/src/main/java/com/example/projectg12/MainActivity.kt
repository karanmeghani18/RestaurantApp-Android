package com.example.projectg12

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.projectg12.databinding.ActivityMainBinding
import com.example.projectg12.models.DataSource
import com.example.projectg12.models.MenuItem
import com.google.gson.Gson
import java.io.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    lateinit var binding: ActivityMainBinding
    lateinit var dataSource: DataSource
    val TAG = "MAIN_ACTIVITY"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController

        binding.bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menuHomescreen -> {
                    navController.navigate(R.id.homescreenFragment, null)
                }
                R.id.menuMenu -> {
                    navController.navigate(R.id.menuFragment, null)
                }
                R.id.menuCart ->{
                    navController.navigate(R.id.cartFragment,null)
                }
                R.id.menuOrderHistory ->{
                    navController.navigate(R.id.orderHistoryFragment,null)
                }
                else -> {

                }
            }
            true
        }

        binding.bottomNavigationView.setOnNavigationItemReselectedListener { item -> return@setOnNavigationItemReselectedListener }


    }


}
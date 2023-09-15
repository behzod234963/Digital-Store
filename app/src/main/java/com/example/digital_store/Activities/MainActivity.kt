package com.example.digital_store.Activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavHost
import com.example.digital_store.R
import com.example.digital_store.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(){

    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()

    }

    private fun initView() {

        val ncNavHost=supportFragmentManager.findFragmentById(R.id.ncNavHost) as NavHost

        navController=ncNavHost.navController
        navController

    }
}
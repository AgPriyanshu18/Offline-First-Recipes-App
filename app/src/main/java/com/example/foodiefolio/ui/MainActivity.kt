package com.example.foodiefolio.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.foodiefolio.R
import com.example.foodiefolio.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
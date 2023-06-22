package com.example.foodiefolio.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.foodiefolio.R
import com.example.foodiefolio.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {

    lateinit var binding : FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(layoutInflater)

        Handler().postDelayed({
            Navigation.findNavController(binding.root).navigate(R.id.action_splashFragment_to_categoriesFragment)
        },2000)

        return binding.root
    }

}
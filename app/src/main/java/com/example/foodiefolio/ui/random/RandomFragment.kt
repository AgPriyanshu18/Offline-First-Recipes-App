package com.example.foodiefolio.ui.random

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.foodiefolio.data.model.MealDetails
import com.example.foodiefolio.databinding.FragmentRandomBinding
import com.example.foodiefolio.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RandomFragment : Fragment() {

    lateinit var binding: FragmentRandomBinding
    private val viewModel by viewModels<RandomViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRandomBinding.inflate(layoutInflater)

        setObservers()

        viewModel.getData()

        return binding.root
    }

    private fun setObservers() {
        binding.apply {
            viewModel.recipe.observe(viewLifecycleOwner) { result ->
                if(result.data != null){
                    binding.compView.visibility = View.VISIBLE
                    updateUI(result.data)
                }
                when (result) {
                    is Resource.Success -> {
                        binding.detailProgressBar.visibility = View.GONE
                        binding.detailsErrorMessage.visibility = View.GONE
                    }
                    is Resource.Error -> {
                        binding.detailProgressBar.visibility = View.GONE
                        binding.detailsErrorMessage.visibility = View.VISIBLE
                    }
                    is Resource.Loading -> {
                        binding.detailProgressBar.visibility = View.VISIBLE
                        binding.detailsErrorMessage.visibility = View.GONE
                    }

                    else -> {}
                }
            }
        }
    }

    private fun updateUI(meal: MealDetails) {
        Log.e("Random Fragment", meal.toString())
        binding.recipeName.text = meal.name
        binding.recipeCatAns.text = meal.category
        binding.recipeAreaAns.text = meal.area
        Glide.with(requireContext())
            .load(meal.Img)
            .circleCrop().
            into(binding.recipeImg)

        binding.Ing1.text = meal.ingredient1
        binding.Ing2.text = meal.ingredient2
        binding.Ing3.text = meal.ingredient3
        binding.Ing4.text = meal.ingredient4
        binding.Ing5.text = meal.ingredient5
        binding.Ms1.text = meal.measure1
        binding.Ms2.text = meal.measure2
        binding.Ms3.text = meal.measure3
        binding.Ms4.text = meal.measure4
        binding.Ms5.text = meal.measure5

        binding.recipePrepSteps.text = meal.instructions
    }

}
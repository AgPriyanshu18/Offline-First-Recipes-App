package com.example.foodiefolio.ui.listing

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.foodiefolio.data.model.Category
import com.example.foodiefolio.data.model.Meals
import com.example.foodiefolio.databinding.FragmentListingBinding
import com.example.foodiefolio.util.AppConstants
import com.example.foodiefolio.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListingFragment : Fragment() {

    lateinit var binding: FragmentListingBinding
    private val viewModel by viewModels<listingViewModel>()
    lateinit var adapter: ListingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListingBinding.inflate(layoutInflater)

        parentFragmentManager.setFragmentResultListener(AppConstants.CATEGORY, this) { _, bundle ->
            val category = bundle.getSerializable(AppConstants.CATEGORY_DATA)
            updateUI(category as Category)
        }

        setObservers()


        return binding.root
    }

    private fun setObservers() {
        binding.apply {
            viewModel.recipe.observe(viewLifecycleOwner) { result ->
                if(result.data != null){
                    setUpRecyclerView(result.data)
                }
                when (result) {
                    is Resource.Success -> {
                        binding.listProgressBar.visibility = View.GONE
                        binding.errorMessage.visibility = View.GONE
                    }
                    is Resource.Error -> {
                        binding.listProgressBar.visibility = View.GONE
                        binding.errorMessage.visibility = View.VISIBLE
                    }
                    is Resource.Loading -> {
                        binding.listProgressBar.visibility = View.VISIBLE
                    }

                    else -> {}
                }
            }
        }
    }

    private fun setUpRecyclerView(meals: List<Meals>) {
        adapter = ListingAdapter(meals,object : ListingAdapter.onClickListener {
            override fun onClick(recipe: Meals) {
                Toast.makeText(requireContext(), recipe.toString(), Toast.LENGTH_SHORT).show()
            }
        })
        binding.listRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.listRecyclerView.setHasFixedSize(true)
        binding.listRecyclerView.adapter = adapter
    }

    private fun updateUI(category: Category) {
        viewModel.getData(category.name)
        Glide.with(requireContext())
            .load(category.Img)
            .centerCrop()
            .into(binding.listCatImage)

        binding.listCatName.text = "Interested in ${category.name} \n Today"
    }

}
package com.example.foodiefolio.ui.search

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodiefolio.R
import com.example.foodiefolio.data.model.Meals
import com.example.foodiefolio.databinding.FragmentSearchBinding
import com.example.foodiefolio.databinding.SearchItemLayoutBinding
import com.example.foodiefolio.util.AppConstants
import com.example.foodiefolio.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    lateinit var binding: FragmentSearchBinding
    private val viewModel by viewModels<SearchViewModel>()
    lateinit var adapter: SearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        setObservers()

        startSearching()

        binding.backButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        return binding.root
    }

    fun startSearching() {
        binding.searchText.doOnTextChanged { text, start, before, count ->
            viewModel.getData(text.toString())
        }
    }

    private fun setObservers() {
        binding.apply {
            viewModel.searchValue.observe(viewLifecycleOwner) { result ->
                Log.d("SearchFragment", result.toString())
                when (result) {
                    is Resource.Success -> {
                        binding.searchProgressBar.visibility = View.GONE
                        binding.searchErrorMessage.visibility = View.GONE
                        Log.d("SearchFragment", result.data.toString())
                        setUpRecyclerView(result.data!!)
                    }

                    is Resource.Error -> {
                        binding.searchProgressBar.visibility = View.GONE
                        binding.searchErrorMessage.visibility = View.VISIBLE
                    }

                    is Resource.Loading -> {
                        binding.searchProgressBar.visibility = View.VISIBLE
                        binding.searchErrorMessage.visibility = View.GONE
                    }

                    else -> {}
                }
            }
        }
    }

    private fun setUpRecyclerView(result: List<Meals>) {
        adapter = SearchAdapter(result, object : SearchAdapter.onClickListener {
            override fun onClick(meal: Meals) {
                val bundle = Bundle()
                bundle.putString(AppConstants.MEAL_ID, meal.id)
                parentFragmentManager.setFragmentResult(AppConstants.MEAL_DATA, bundle)
                val k = parentFragmentManager.findFragmentById(R.id.fragmentView)
                Navigation.findNavController(k!!.requireView()).navigate(R.id.action_searchFragment_to_detailsFragment)
            }
        })
        binding.searchRecyclerView.setHasFixedSize(true)
        binding.searchRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.searchRecyclerView.adapter = adapter
    }
}
package com.example.foodiefolio.ui.categories

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.foodiefolio.R
import com.example.foodiefolio.data.model.Category
import com.example.foodiefolio.databinding.FragmentCategoriesBinding
import com.example.foodiefolio.ui.search.SearchFragment
import com.example.foodiefolio.util.AppConstants
import com.example.foodiefolio.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesFragment : Fragment() {

    lateinit var binding : FragmentCategoriesBinding
    private val viewModel by viewModels<categoryViewModel>()
    lateinit var adapter: CategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoriesBinding.inflate(layoutInflater)

        setUpObservers()

        viewModel.refresh()

        return binding.root
    }

    private fun setUpRecyclerView(list : List<Category>) {
        adapter = CategoryAdapter(list, object : CategoryAdapter.onClickListener {
            override fun onClick(category: Category) {
                val bundle = Bundle()
                bundle.putSerializable(AppConstants.CATEGORY_DATA, category)
                parentFragmentManager.setFragmentResult(AppConstants.CATEGORY, bundle)
                val k = parentFragmentManager.findFragmentById(R.id.fragmentView)
                Log.e("Category fragment" , k.toString())
                Navigation.findNavController(k!!.requireView()).navigate(R.id.action_categoriesFragment_to_listingFragment)
            }
        })
        binding.categoriesRecyclerView.layoutManager = GridLayoutManager(requireContext(),2)
        binding.categoriesRecyclerView.setHasFixedSize(true)
        binding.categoriesRecyclerView.adapter = adapter
    }

    private fun setUpObservers() {
        binding.apply {
            viewModel.categories.observe(viewLifecycleOwner) { result ->
                if(result.data != null){
                    binding.catProgressBar.visibility = View.INVISIBLE
                    binding.errorMessage.visibility = View.INVISIBLE
                    setUpRecyclerView(result.data)
                }
                when (result) {
                    is Resource.Success -> {
                        binding.catProgressBar.visibility = View.INVISIBLE
                        binding.errorMessage.visibility = View.INVISIBLE
                    }
                    is Resource.Loading -> {
                        binding.catProgressBar.visibility = View.VISIBLE
                        binding.errorMessage.visibility = View.INVISIBLE
                    }
                    is Resource.Error -> {
                        binding.catProgressBar.visibility = View.INVISIBLE
                        binding.errorMessage.visibility = View.VISIBLE
                    }

                    else -> {}
                }
            }
        }
    }

}
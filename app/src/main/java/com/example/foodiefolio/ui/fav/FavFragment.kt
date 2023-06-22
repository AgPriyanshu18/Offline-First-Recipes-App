package com.example.foodiefolio.ui.fav

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodiefolio.R
import com.example.foodiefolio.data.model.Favourite
import com.example.foodiefolio.databinding.FragmentFavBinding
import com.example.foodiefolio.util.AppConstants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavFragment : Fragment() {

    lateinit var binding: FragmentFavBinding
    private val viewModel by viewModels<FavViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavBinding.inflate(layoutInflater)

        setUpObersvers()

        binding.backButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        return binding.root
    }

    private fun setUpObersvers() {
        binding.apply {
            viewModel.favList.observe(viewLifecycleOwner) { result ->
                if(result.data != null){
                    Log.d("FavFragment", "setUpObersvers: ${result.data}")
                    setUpRecyclerView(result.data)
                }
            }
        }
    }

    private fun setUpRecyclerView(data: List<Favourite>) {
        binding.apply {
            favRecyclerView.adapter = FavAdapter(data, object : FavAdapter.onClickListener {
                override fun onClick(recipe: Favourite) {
                    val bundle = Bundle()
                    bundle.putString(AppConstants.MEAL_ID, recipe.id)
                    parentFragmentManager.setFragmentResult(AppConstants.MEAL_DATA, bundle)
                    val k = parentFragmentManager.findFragmentById(R.id.fragmentView)
                    Navigation.findNavController(k!!.requireView()).navigate(R.id.action_favFragment_to_detailsFragment)
                }
            })
            favRecyclerView.setHasFixedSize(true)
            favRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        }
    }

}
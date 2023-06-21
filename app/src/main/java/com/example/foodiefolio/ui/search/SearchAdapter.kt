package com.example.foodiefolio.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodiefolio.data.model.Meals
import com.example.foodiefolio.databinding.SearchItemLayoutBinding

class SearchAdapter(
    private var results : List<Meals> ,
    private var OnItemClickListener: onClickListener
    ) :
    RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding =
            SearchItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val model = results[position]

        holder.name.text = model.name

        Glide.with(holder.itemView.context)
            .load(model.Img)
            .centerCrop()
            .into(holder.image)

        holder.itemView.setOnClickListener {
            OnItemClickListener.onClick(model)
        }
    }

    override fun getItemCount(): Int {
        return results.size
    }

    class SearchViewHolder(private val binding: SearchItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val image = binding.recipeImage

        val name = binding.recipeName
    }

    interface onClickListener {
        fun onClick(recipe: Meals)
    }
}
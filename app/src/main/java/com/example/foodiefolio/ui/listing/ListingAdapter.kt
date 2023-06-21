package com.example.foodiefolio.ui.listing

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodiefolio.data.model.Meals
import com.example.foodiefolio.databinding.SearchItemLayoutBinding
import com.example.foodiefolio.ui.categories.CategoryAdapter

class ListingAdapter(private var recipes: List<Meals>, private var OnItemClickListener: onClickListener
) : RecyclerView.Adapter<ListingAdapter.ListingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListingViewHolder {
        val binding =
            SearchItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListingViewHolder, position: Int) {
        val model = recipes[position]

        Glide.with(holder.itemView.context)
            .load(model.Img)
            .centerCrop()
            .into(holder.image)

        holder.name.text = model.name

        holder.itemView.setOnClickListener {
            OnItemClickListener.onClick(model)
        }
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    class ListingViewHolder(private val binding: SearchItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val image = binding.recipeImage

        val name = binding.recipeName
    }

    interface onClickListener {
        fun onClick(recipe: Meals)
    }
}
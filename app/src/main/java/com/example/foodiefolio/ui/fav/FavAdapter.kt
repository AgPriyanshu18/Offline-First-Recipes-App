package com.example.foodiefolio.ui.fav

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodiefolio.data.model.Favourite
import com.example.foodiefolio.databinding.SearchItemLayoutBinding

class FavAdapter (private var recipe : List<Favourite> , private var OnItemClickListener: onClickListener) :
    RecyclerView.Adapter<FavAdapter.FavViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavAdapter.FavViewHolder {
        val binding = SearchItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavViewHolder, position: Int) {
        val model = recipe[position]

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
        return recipe.size
    }

    class FavViewHolder(private val binding: SearchItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

            val image = binding.recipeImage
            val name = binding.recipeName

    }

    interface onClickListener {
        fun onClick(recipe: Favourite)
    }

}
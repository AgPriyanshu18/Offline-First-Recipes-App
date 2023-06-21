package com.example.foodiefolio.ui.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodiefolio.data.model.Category
import com.example.foodiefolio.databinding.CategoryItemLayoutBinding

class CategoryAdapter(private var categories: List<Category>, private var OnItemClickListener: onClickListener
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = CategoryItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val model = categories[position]

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
        return categories.size
    }

    class CategoryViewHolder(private val binding: CategoryItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        val image = binding.recipeImage

        val name = binding.recipeName


    }

    interface onClickListener{
        fun onClick(category : Category)
    }
}
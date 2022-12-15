package com.example.projectg12.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectg12.databinding.CategoryItemBinding

class CategoryAdapter(
    private val context: Context,
    private var categoryList: List<String>
): RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    internal val TAG = "Category List Adapter"

    class CategoryViewHolder (private val binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(currentItem: String) {
            // associate individual view with data
            binding.tvCategory.text = currentItem
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryAdapter.CategoryViewHolder {
        return CategoryAdapter.CategoryViewHolder(
            CategoryItemBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CategoryAdapter.CategoryViewHolder, position: Int) {
        holder.bind(categoryList[position])
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }
}

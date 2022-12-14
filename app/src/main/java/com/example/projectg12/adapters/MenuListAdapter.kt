package com.example.projectg12.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectg12.databinding.MenuItemBinding
import com.example.projectg12.interfaces.IOnMenuItemClickListener
import com.example.projectg12.models.MenuItem

class MenuListAdapter(
    private val context: Context,
    private var menuItemsList: List<MenuItem>,
    private val clickListener: IOnMenuItemClickListener
) : RecyclerView.Adapter<MenuListAdapter.MenuViewHolder>() {
    internal val TAG = "Menu Adapter"


    class MenuViewHolder(val binding: MenuItemBinding) :
        RecyclerView.ViewHolder(binding.root) {


        @SuppressLint("SetTextI18n")
        fun bind(currentItem: MenuItem, clickListener: IOnMenuItemClickListener) {
            // associate individual view with data
            binding.itemName.text = currentItem.itemName
            binding.itemDesc.text = currentItem.description
            binding.itemPrice.text = currentItem.price.toString()

            binding.btnAdd.setOnClickListener {
                clickListener.onMenuItemClickListener(currentItem)
            }

        }
    }

    // create the view
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MenuViewHolder {
        return MenuViewHolder(
            MenuItemBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return menuItemsList.size
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bind(menuItemsList[position], clickListener)
    }
}
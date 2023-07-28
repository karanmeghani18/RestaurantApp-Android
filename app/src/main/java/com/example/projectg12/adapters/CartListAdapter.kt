package com.example.projectg12.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.projectg12.databinding.CartItemBinding
import com.example.projectg12.databinding.MenuItemBinding
import com.example.projectg12.interfaces.IOnMenuItemClickListener
import com.example.projectg12.interfaces.IonCartItemClickListener
import com.example.projectg12.models.MenuItem

class CartListAdapter(
    private val context: Context,
    private var cartItemsList: List<MenuItem>,
    private val clickListener: IonCartItemClickListener
) : RecyclerView.Adapter<CartListAdapter.CartViewHolder>() {
    internal val TAG = "CART ADAPTER"


    class CartViewHolder(private val binding: CartItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(currentItem: MenuItem, clickListener: IonCartItemClickListener) {
            binding.itemName.text = currentItem.name
            binding.itemPrice.text = currentItem.price.toString()

            binding.btnRemove.setOnClickListener {
                clickListener.onCartItemDelete(currentItem)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        return CartViewHolder(
            CartItemBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(cartItemsList[position], clickListener)
    }

    override fun getItemCount(): Int {
        return cartItemsList.size
    }

}

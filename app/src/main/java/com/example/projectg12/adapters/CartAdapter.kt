package com.example.projectg12.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectg12.data.Cart
import com.example.projectg12.databinding.ItemCartBinding

class CartAdapter (val context: Context, var dataSource: ArrayList<Cart>) :
    RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    class CartViewHolder(var binding: ItemCartBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(currentCart:Cart){

            binding.tvTitle.setText(currentCart.item)
            binding.tvPrice.setText(currentCart.price.toString())
            binding.tvQuantity.setText(currentCart.quantity.toString())
            binding.tvTotal.setText(currentCart.total.toString())

            itemView.setOnClickListener{
                //Log.e("CountryViewHolder", currentCountry.name.common)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        return CartViewHolder(ItemCartBinding.inflate(LayoutInflater.from(context),parent,false))
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(dataSource.get(position))
    }

    override fun getItemCount(): Int {
        return dataSource.size
    }
}
package com.example.projectg12.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectg12.databinding.HistoryItemBinding
import com.example.projectg12.models.Order

class OrderHistoryAdapter(
    private val context: Context,
    private var orderHistoryList: List<Order>
): RecyclerView.Adapter<OrderHistoryAdapter.OrderHistoryViewHolder>() {
    internal val TAG = "OrderHistory Adapter"

    class OrderHistoryViewHolder(private val binding: HistoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(currentItem: Order) {
            // associate individual view with data
            binding.tvOrderHistory.text = currentItem.orderItemId.toString()
            binding.tvDateTime.text = currentItem.dateAndTimeOfOrder.toString()
            binding.tvTotal.text = currentItem.totalAmount.toString()
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OrderHistoryAdapter.OrderHistoryViewHolder {
        return OrderHistoryAdapter.OrderHistoryViewHolder(
            HistoryItemBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: OrderHistoryViewHolder, position: Int) {
        holder.bind(orderHistoryList[position])
    }

    override fun getItemCount(): Int {
        return orderHistoryList.size
    }
}

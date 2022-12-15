package com.example.projectg12.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectg12.adapters.OrderHistoryAdapter
import com.example.projectg12.databinding.FragmentOrderHistoryBinding
import com.example.projectg12.models.DataSource
import com.example.projectg12.models.Order
import com.example.projectg12.repository.CartRepo
import com.example.projectg12.repository.MenuRepo


class OrderHistoryFragment : Fragment() {
    val TAG: String = "ORDE RHISTORY-FRAGMENT"
    private var _binding: FragmentOrderHistoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var orderHistoryAdapter: OrderHistoryAdapter
    private lateinit var dataSource: DataSource
    private lateinit var orderHistoryList: List<Order>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentOrderHistoryBinding.inflate(inflater, container, false)
        dataSource = DataSource.getInstance()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        orderHistoryList = dataSource.userOrderHistory
        orderHistoryAdapter = OrderHistoryAdapter(view.context,orderHistoryList)
        binding.rvOrderHistory.adapter = orderHistoryAdapter
        binding.rvOrderHistory.layoutManager = LinearLayoutManager(view.context)

    }


}
package com.example.projectg12.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectg12.adapters.CartListAdapter
import com.example.projectg12.databinding.FragmentCartBinding
import com.example.projectg12.interfaces.IonCartItemClickListener
import com.example.projectg12.models.DataSource
import com.example.projectg12.models.MenuItem
import com.example.projectg12.repository.CartRepo
import com.example.projectg12.repository.MenuRepo
import kotlinx.coroutines.launch
import okhttp3.internal.format

class CartFragment : Fragment(), IonCartItemClickListener {
    val TAG: String = "CART-FRAGMENT"
    var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!
    private lateinit var menuRepo: MenuRepo
    private lateinit var cartRepo: CartRepo
    private lateinit var cartListAdapter: CartListAdapter
    private lateinit var dataSource: DataSource
    private lateinit var cartItemsList: ArrayList<MenuItem>
    var subtotal: Double = 0.0
    var tax: Double = 0.0
    var total: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        dataSource = DataSource.getInstance()
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cartRepo = CartRepo(this)
        cartItemsList = ArrayList(dataSource.userCartList)
        Log.d(TAG, "onViewCreated: $cartItemsList")
        cartListAdapter = CartListAdapter(view.context, cartItemsList, this)
        binding.rvCart.adapter = cartListAdapter
        binding.rvCart.layoutManager = LinearLayoutManager(view.context)
        binding.btnCheckout.setOnClickListener{
            onCheckOutCLickListener()
        }

    }

    override fun onResume() {
        super.onResume()
        cartItemsList.clear()
        cartRepo.getUsersCart()


    }

    override fun onCartItemDelete(cartItem: MenuItem) {
        Log.d(TAG, "onCartItemDelete: $cartItem")
        viewLifecycleOwner.lifecycleScope.launch {
            cartRepo.removeCartItem(cartItem)
        }


    }

    override fun onCartItemsChangedListener() {
        cartItemsList = dataSource.userCartList
        Log.d(TAG, "onCartItemsChangedListener: $cartItemsList")
        cartListAdapter = CartListAdapter(requireContext(), cartItemsList, this)
        binding.rvCart.adapter = cartListAdapter
        Log.d(TAG, "onCartItemsChangedListener: ${(binding.rvCart.adapter)?.itemCount}")
        subtotal = cartRepo.getCartTotal()
        tax = subtotal*0.13
        total = subtotal+tax

        binding.tvSubtotal.text = "Subtotal: $${format("%.2f",subtotal)}"
        binding.tvTax.text = "TAX @ 13%: $${format("%.2f",tax)}"
        binding.tvTotal.text = "total: $${format("%.2f",total)}"


    }

     private fun onCheckOutCLickListener() {
         lifecycleScope.launch {
             cartRepo.checkOut(total = total)
             cartRepo.resetCart()
         }

    }
}
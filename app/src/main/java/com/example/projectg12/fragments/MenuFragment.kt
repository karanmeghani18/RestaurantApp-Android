package com.example.projectg12.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectg12.R
import com.example.projectg12.adapters.MenuListAdapter
import com.example.projectg12.databinding.FragmentMenuBinding
import com.example.projectg12.interfaces.IOnMenuItemClickListener
import com.example.projectg12.interfaces.IonCartItemClickListener
import com.example.projectg12.models.DataSource
import com.example.projectg12.repository.MenuRepo
import com.example.projectg12.models.MenuItem
import com.example.projectg12.repository.CartRepo
import kotlinx.coroutines.launch

class MenuFragment : Fragment(), IOnMenuItemClickListener, IonCartItemClickListener {
    val TAG: String = "SIGNUP-FRAGMENT"
    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!
    private lateinit var menuRepo: MenuRepo
    private lateinit var cartRepo: CartRepo
    private lateinit var menuListAdapter: MenuListAdapter
    private lateinit var dataSource: DataSource
    private lateinit var menuItemsList: ArrayList<MenuItem>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        dataSource = DataSource.getInstance()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        menuItemsList = dataSource.menuItemList
        menuRepo = MenuRepo(this)
        cartRepo = CartRepo(this)
        menuListAdapter = MenuListAdapter(view.context, menuItemsList = menuItemsList, this)
        binding.rvMenu.adapter = menuListAdapter
        binding.rvMenu.layoutManager = LinearLayoutManager(view.context)


    }

    override fun onResume() {
        super.onResume()
        menuItemsList.clear()
        menuRepo.getMenuItems()
    }

    override fun onMenuItemClickListener(menuItem: MenuItem) {
        Log.d(TAG, "onMenuItemClickListener: ${menuItem.id}")
        dataSource.userCartList += menuItem

        Toast.makeText(requireContext(), "Added ${menuItem.name} to the cart", Toast.LENGTH_SHORT)
            .show()
        Log.d(TAG, "onMenuItemClickListener: ${dataSource.userCartList}")
        lifecycleScope.launch {
            cartRepo.addToCart(menuItem)
        }
    }

    override fun onMenuItemsChangeListener() {
        Log.d(TAG, "onMenuItemsChangeListener: Reached the change listener")
        menuItemsList = dataSource.menuItemList
        Log.d(TAG, "onMenuItemsChangeListener: $menuItemsList")
        menuListAdapter = MenuListAdapter(requireContext(), menuItemsList = menuItemsList, this)
        binding.rvMenu.adapter = menuListAdapter
        Log.d(TAG, "onMenuItemsChangeListener: ${menuListAdapter.itemCount}")
    }

    override fun onCartItemDelete(cartItem: MenuItem) {

    }

    override fun onCartItemsChangedListener() {

    }

}
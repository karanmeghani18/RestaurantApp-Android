package com.example.projectg12.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectg12.adapters.MenuListAdapter
import com.example.projectg12.databinding.FragmentMenuBinding
import com.example.projectg12.interfaces.IOnMenuItemClickListener
import kotlinx.coroutines.launch
import com.example.projectg12.models.MenuItem

class MenuFragment : Fragment(),IOnMenuItemClickListener {
    val TAG: String = "SIGNUP-FRAGMENT"
    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!
//    private lateinit var menuRepo:MenuRepo
    var menuListAdapter:MenuListAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var menuArrayList = arrayListOf<MenuItem>()
//        menuRepo = MenuRepo()
        menuListAdapter = MenuListAdapter(view.context, menuItemsList =menuArrayList ,this )
        binding.rvMenu.adapter= menuListAdapter
        binding.rvMenu.layoutManager = LinearLayoutManager(view.context)


        viewLifecycleOwner.lifecycleScope.launch {

        }


    }

    override fun onMenuItemClickListener(menuItem: MenuItem) {
        TODO("Not yet implemented")
    }

}
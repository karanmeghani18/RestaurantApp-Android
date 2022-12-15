package com.example.projectg12.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectg12.R
import com.example.projectg12.adapters.CategoryAdapter
import com.example.projectg12.databinding.FragmentHomescreenBinding
import com.example.projectg12.databinding.FragmentMenuBinding
import com.example.projectg12.interfaces.IOnMenuItemClickListener
import com.example.projectg12.models.DataSource
import com.example.projectg12.models.MenuItem
import com.example.projectg12.repository.MenuRepo

class HomescreenFragment : Fragment(), IOnMenuItemClickListener {

    private lateinit var menuRepo: MenuRepo
    private lateinit var dataSource: DataSource
    val TAG: String = "HomeScreen-FRAGMENT"
    private var _binding: FragmentHomescreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomescreenBinding.inflate(inflater, container, false)
        dataSource = DataSource.getInstance()
        return binding.root    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        menuRepo = MenuRepo(this)
        menuRepo.getMenuItems()
        binding.textView.text= "Hi! ${dataSource.currentUser!!.name}"
        var fullMenu = dataSource.menuItemList
        var categories: List<String?> = listOf()
        for (i in fullMenu){
            categories += listOf(i.category)
        }
        binding.categoryRecyclerView.adapter = CategoryAdapter(view.context, categoryList = categories as List<String>)
        binding.categoryRecyclerView.layoutManager = LinearLayoutManager(requireContext())


    }

    override fun onMenuItemClickListener(menuItem: MenuItem) {

    }

    override fun onMenuItemsChangeListener() {

    }
}
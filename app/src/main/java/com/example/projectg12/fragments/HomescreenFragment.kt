package com.example.projectg12.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.projectg12.R
import com.example.projectg12.interfaces.IOnMenuItemClickListener
import com.example.projectg12.models.MenuItem
import com.example.projectg12.repository.MenuRepo

class HomescreenFragment : Fragment(), IOnMenuItemClickListener {

    private lateinit var menuRepo: MenuRepo

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_homescreen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        menuRepo = MenuRepo(this)
        menuRepo.getMenuItems()
    }

    override fun onMenuItemClickListener(menuItem: MenuItem) {

    }

    override fun onMenuItemsChangeListener() {

    }
}
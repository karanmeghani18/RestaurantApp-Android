package com.example.projectg12.interfaces

import com.example.projectg12.models.MenuItem

interface IOnMenuItemClickListener {

    fun onMenuItemClickListener(menuItem: MenuItem)

    fun onMenuItemsChangeListener()
}
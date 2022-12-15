package com.example.projectg12.interfaces

import com.example.projectg12.models.MenuItem

interface IonCartItemClickListener {
    fun onCartItemDelete(cartItem: MenuItem)

    fun onCartItemsChangedListener()

}

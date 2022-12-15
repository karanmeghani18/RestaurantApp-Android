package com.example.projectg12.data

import android.app.Application
import android.provider.ContactsContract
import androidx.lifecycle.LiveData

class CartRepository (application: Application) {
    private val db : AppDB?
    private val cartDAO = application.let { AppDB.getDB(application)!!.cartDAO() }
    val allCart : LiveData<List<Cart>> = cartDAO.getAllCart()

    init {
        db = AppDB.getDB(application!!)
    }

    suspend fun insertCart(newCart: Cart){
        AppDB.databaseWriteExecutor.execute { cartDAO.insertCart(newCart) }
    }

    suspend fun updateCart(newNote: Cart){
        AppDB.databaseWriteExecutor.execute { cartDAO.updateCart(newNote) }
    }

    suspend fun deleteCart(newNote: Cart){
        AppDB.databaseWriteExecutor.execute { cartDAO.deleteCart(newNote) }
    }

    suspend fun deleteAllCart(){
        AppDB.databaseWriteExecutor.execute { cartDAO.deleteAllCart() }
    }
}
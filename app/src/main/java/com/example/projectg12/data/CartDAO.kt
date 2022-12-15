package com.example.projectg12.data

import android.provider.ContactsContract
import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface CartDAO {

    @Insert
    fun insertCart(cart: Cart)
    //INSERT INTO table_note VALUES('check query operation', 'some info', 3)

    @Update
    fun updateCart(cart: Cart)

    @Delete
    fun deleteCart(cart: Cart)

    @Query("DELETE FROM table_cart")
    fun deleteAllCart()

    //    @Query("SELECT * FROM table_note ORDER BY priority DESC")
    @Query("SELECT * FROM table_cart")
    fun getAllCart() : LiveData<List<Cart>>
}

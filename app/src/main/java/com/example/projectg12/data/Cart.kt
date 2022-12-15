package com.example.projectg12.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_cart")
class Cart(var item:String , var quantity: Int , var price:Int , var total:Int) {

    @PrimaryKey(autoGenerate = true)

   var id = 0
}

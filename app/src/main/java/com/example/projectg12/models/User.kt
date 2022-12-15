package com.example.projectg12.models

import android.util.Log
import com.google.firebase.firestore.DocumentId

class User(
    @DocumentId
    var id: String,
    var cartItemIds: List<String>,
    var email: String,
    var name: String,
    var orderHistory: List<Order>
) {
    override fun toString(): String {
        return "User(id='$id', cartItemIds=$cartItemIds, email='$email', name='$name', orderHistory=$orderHistory)"
    }

    fun toMap(): Map<String, Any> {
        val mapOrderHistory = orderHistory.map {
            it.toMap()
        }.toList()
        return mapOf(
            Pair("cartItemIds", this.cartItemIds),
            Pair("email", this.email),
            Pair("name", this.name),
            Pair("orderHistory", mapOrderHistory),
        )
    }

    companion object {
        public fun fromMap(map: Map<String, Any>, id: String): User {
            val ordH = map["orderHistory"] as List<Map<String, Any>?>
            var orderHistoryList: List<Order> = mutableListOf()
            if (ordH != null) {
                ordH.forEach {
                    val order = Order.fromMap(it!!)
                    orderHistoryList += order
                }
            }

            Log.d("USER_OBJ", "fromMap: ${map["cartItemIds"]}")
            return User(
                id = id,
                cartItemIds = if (map["cartItemIds"] == null) listOf() else map["cartItemIds"] as List<String>,
                email = map["email"] as String,
                name = map["name"] as String,
                orderHistory = orderHistoryList
            )
        }
    }


}
package com.example.projectg12.models

import com.google.firebase.Timestamp
import java.util.Date

class Order(
    var dateAndTimeOfOrder: Timestamp,
    var orderItemId: List<String>,
    var totalAmount: Double
) {
    override fun toString(): String {
        return "Order(dateAndTimeOfOrder=$dateAndTimeOfOrder, orderItemId=$orderItemId, totalAmount=$totalAmount)"
    }

    fun toMap(): Map<String, Any> {
        return mapOf<String, Any>(
            Pair("dateAndTimeOfOrder", this.dateAndTimeOfOrder),
            Pair("orderItemId", this.orderItemId),
            Pair("totalAmount", this.totalAmount),
        )
    }


    companion object {
        public fun fromMap(map: Map<String, Any>): Order {
            return Order(
                dateAndTimeOfOrder = map["dateAndTimeOfOrder"] as Timestamp,
                orderItemId = map["orderItemId"] as List<String>,
                totalAmount = map["totalAmount"] as Double,
            )
        }
    }
}
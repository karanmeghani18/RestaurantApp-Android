package com.example.projectg12.models

import android.graphics.ColorSpace.Model
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.Exclude


enum class MenuCategory {
    Pasta,
    Pizza,
    Desserts,
    Appetizers
}

data class MenuItem(
    var category: String? = null,
    var description: String? = null,
    var name: String? = null,
    var price: Double? = null,
    @DocumentId
    var id: String? = null,
) : java.io.Serializable {
    override fun toString(): String {
        return "MenuItem(category=$category, description=$description, name=$name, price=$price, id=$id)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MenuItem

        if (category != other.category) return false
        if (description != other.description) return false
        if (name != other.name) return false
        if (price != other.price) return false
        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        var result = category?.hashCode() ?: 0
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (price?.hashCode() ?: 0)
        result = 31 * result + (id?.hashCode() ?: 0)
        return result
    }


}


package com.example.projectg12.models

enum class MenuCategory {
    Pasta,
    Pizza,
    Desserts,
    Appetizers
}

class MenuItem(
    var category: MenuCategory,
    var itemName: String,
    var description: String,
    var price: Double,
) : java.io.Serializable {
}
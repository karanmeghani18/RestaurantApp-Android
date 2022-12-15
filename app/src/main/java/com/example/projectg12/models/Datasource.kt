package com.example.projectg12.models


open class DataSource {

    companion object {
        @Volatile
        private lateinit var instance: DataSource

        fun getInstance(): DataSource {
            synchronized(this) {
                if (!::instance.isInitialized) {
                    instance = DataSource()
                }
                return instance
            }
        }
    }

    var menuItemList: ArrayList<MenuItem> = arrayListOf()
    var userCartList: ArrayList<MenuItem> = arrayListOf()
    var currentUser: User? = null
}
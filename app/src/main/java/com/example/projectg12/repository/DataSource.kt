package com.example.projectg12.repository

open class DataSource {

    companion object{
        @Volatile
        private lateinit var instance: DataSource

        fun getInstance(): DataSource {
            synchronized(this){
                if (!::instance.isInitialized){
                    instance = DataSource()
                }
                return instance
            }
        }
    }
}
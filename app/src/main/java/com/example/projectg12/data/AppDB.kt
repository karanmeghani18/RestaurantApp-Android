package com.example.projectg12.data

import android.content.Context
import android.provider.ContactsContract
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.util.concurrent.Executors

@Database(entities = [Cart::class], version = 1, exportSchema = false)
abstract class AppDB : RoomDatabase(){

    abstract fun cartDAO() : CartDAO

    companion object {
        @Volatile
        private var db: AppDB? = null
        private const val NUMBER_OF_THREADS = 4
        val databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS)

        fun getDB(context: Context): AppDB? {
            if (db == null) {
                db = Room.databaseBuilder(
                    context.applicationContext,
                    AppDB::class.java,
                    "com_jk_notes_db"
                ).fallbackToDestructiveMigration().build()
            }
            return db
        }
    }
}









package com.example.projectg12.repository

import android.util.Log
import com.example.projectg12.interfaces.IOnMenuItemClickListener
import com.example.projectg12.models.DataSource
import com.example.projectg12.models.MenuItem
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MenuRepo(
    private var menuItemClickListener: IOnMenuItemClickListener
) {
    private val TAG = "MENU_REPO"
    private val db = Firebase.firestore
    private val COLLECTION_NAME = "menuItems"
    private var dataSource: com.example.projectg12.models.DataSource = DataSource.getInstance()


    fun getMenuItems() {
        var menuArrayList: List<MenuItem> = mutableListOf()
        try {
            db.collection(COLLECTION_NAME).addSnapshotListener(EventListener { snapshot, error ->

                if (error != null) {
                    Log.e(TAG, "SyncPosts: Listening to collection documents failed $error")
                    return@EventListener
                }
                if (snapshot != null) {

                    menuArrayList = snapshot.toObjects(MenuItem::class.java)
                    Log.d(
                        TAG,
                        "SyncPosts: ${snapshot.size()} Received the documents from collection $snapshot"
                    )

                    Log.d(TAG, menuArrayList.toString())
                    dataSource.menuItemList = ArrayList(menuArrayList)
                    menuItemClickListener.onMenuItemsChangeListener()
                }
            }
            )
        } catch (ex: Exception) {
            Log.d(TAG, "SyncPosts: exception: $ex")
        }
    }
}
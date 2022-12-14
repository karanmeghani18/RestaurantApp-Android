package com.example.projectg12.repository

import android.util.Log
import com.example.projectg12.models.MenuItem
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.tasks.asDeferred

class MenuRepo {
    private val TAG = "MENU_REPO"
    private val db = Firebase.firestore
    private val COLLECTION_NAME = "menuItems"

    suspend fun getAllMenuItems() {

        try {
            db.collection(COLLECTION_NAME).get().addOnSuccessListener { result->
//                val mnu = result.toObjects<MenuItem()
            }
        } catch (ex: Exception) {
            Log.d(TAG, "SyncPosts: exception: $ex")
        }
}}
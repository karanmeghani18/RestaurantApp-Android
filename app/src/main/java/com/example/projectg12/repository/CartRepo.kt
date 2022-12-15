package com.example.projectg12.repository

import android.util.Log
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.lang.Exception

class CartRepo {
    private val db = Firebase.firestore
    private val COLLECTION_NAME = "users"
    private val TAG = "CartRepo"


    fun getAllCart(){

        try{
//            db.collection(COLLECTION_NAME)
//                .whereEqualTo(FEILD_FRUIT_NAME, "Mango")
//                .orderBy(FEILD_FRUIT_NAME, Query.Direction.DESCENDING)
//                .addSnapshotListener()

            db.collection(COLLECTION_NAME).addSnapshotListener(EventListener { snapshot, error ->
                if (error != null){
                    Log.e(TAG, "getAllCartDetails: Listening to collection documents FAILED ${error}", )
                    return@EventListener
                }

                if (snapshot != null){
                    Log.e(TAG, "getAllCartDetails: ${snapshot.size()} Received the documents from collection ${snapshot}", )

                }else{
                    Log.e(TAG, "getAllCartDetails: No Documents received from collection", )
                }

            })

        }catch(ex: Exception){
            Log.e(TAG, "getAllCartDetails:: ${ex}", )
        }
    }
}
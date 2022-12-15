package com.example.projectg12.repository

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.tasks.asDeferred

class UsersRepo {
    private val  TAG = this.toString()
    private val db = Firebase.firestore
    private val COLLECTION_NAME = "users"
    private val FIELD_NAME = "name"
    suspend fun addUserToDB(newUser: com.example.projectg12.models.User){

        val data: MutableMap<String, Any> = HashMap() // needed to store to FireStore collection

        data[FIELD_NAME] = newUser.name
        val task: Task<Void> = db.collection(COLLECTION_NAME).document(newUser.id).set(data)
                .addOnSuccessListener { doc ->
                    Log.d(TAG, "addUserToDB - added document: $doc")
                }.addOnFailureListener { ex ->
                    Log.d(TAG, "addUserToDB: $ex")
                }

        val deferredDataSnapshot: Deferred<Void> = task.asDeferred()
        val result: Void = deferredDataSnapshot.await()
    }

}
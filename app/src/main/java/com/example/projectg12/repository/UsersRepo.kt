package com.example.projectg12.repository

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.tasks.asDeferred
import com.example.projectg12.models.User
import com.google.firebase.firestore.DocumentSnapshot

class UsersRepo {
    private val TAG = this.toString()
    private val db = Firebase.firestore
    private val COLLECTION_NAME = "users"
    suspend fun addUserToDB(newUser: User) {
        val task: Task<Void> =
            db.collection(COLLECTION_NAME).document(newUser.id).set(newUser.toMap())
                .addOnSuccessListener { doc ->
                    Log.d(TAG, "addUserToDB - added document: $doc")
                }.addOnFailureListener { ex ->
                    Log.d(TAG, "addUserToDB: $ex")
                }
        val deferredDataSnapshot: Deferred<Void> =
            task.asDeferred()
        val result: Void = deferredDataSnapshot.await()
    }

    suspend fun getUserFromUID(userUID: String): User? {
        var user: User? = null
        val task: Task<DocumentSnapshot> =
            db.collection(COLLECTION_NAME).document(userUID)
                .get()
                .addOnSuccessListener {
                    if (it.exists()) {
                        user = User.fromMap(it.data as Map<String, Any>, userUID)
                        Log.d(TAG, "User from UID: $user")
                    }
                }.addOnFailureListener {
                    user = null
                }
        task.asDeferred().await()
        return user

    }

}
package com.example.projectg12.repository

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import kotlinx.coroutines.tasks.asDeferred

class AuthRepo() {
    var TAG = "PROJECT G12"
    var mAuth = FirebaseAuth.getInstance()

    suspend fun createAccount(context: Context, email: String, password: String): String? {
        //SignUp
        var userUID: String? = null
        val task: Task<AuthResult> = mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    userUID = mAuth.currentUser?.uid
                    // account successfully created
                    Log.d(TAG, "SignUpFragment: Created account successfully")
                } else {
                    // Failed to create account
                    Log.d(TAG, "SignUpFragment:  ${task.exception}")
                    Toast.makeText(context, "${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
        val deferredDataSnapshot: kotlinx.coroutines.Deferred<AuthResult> = task.asDeferred()
        val data: AuthResult = deferredDataSnapshot.await()
        return userUID
    }

    suspend fun signIn(context: Context, email: String, password: String): Boolean {
        var validate = false
        try {
            val task: Task<AuthResult> = mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        validate = true
                    } else {
                        Toast.makeText(context, "Invalid email and password.", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            val deferredDataSnapshot: kotlinx.coroutines.Deferred<AuthResult> = task.asDeferred()
            deferredDataSnapshot.await()
        } catch (e: FirebaseAuthInvalidUserException) {
            Log.d(TAG, "signIn: ${e.cause?.message}")
        }
        return validate
    }
}
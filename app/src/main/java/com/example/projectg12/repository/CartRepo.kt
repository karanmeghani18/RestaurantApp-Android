package com.example.projectg12.repository

import android.util.Log
import android.widget.Toast
import com.example.projectg12.interfaces.IOnMenuItemClickListener
import com.example.projectg12.interfaces.IonCartItemClickListener
import com.example.projectg12.models.DataSource
import com.example.projectg12.models.MenuItem
import com.example.projectg12.models.User
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseException
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.tasks.asDeferred

class CartRepo(
    var clickListener: IonCartItemClickListener
) {
    val TAG = "CART_REPO"
    private val db = Firebase.firestore
    private val COLLECTION_NAME = "users"
    private var dataSource: DataSource = DataSource.getInstance()

    suspend fun addToCart(menuItem: MenuItem): Boolean {
        val currentUser: User? = dataSource.currentUser
        var isSuccess: Boolean = false

        if (currentUser != null) {
            val task: Task<Void> =
                db.collection(COLLECTION_NAME).document(currentUser!!.id)
                    .update("cartItemIds", FieldValue.arrayUnion(menuItem.id))
                    .addOnSuccessListener { doc ->
                        Log.d(TAG, "addToCart: Successfully added item to cart on firestore ")
                        isSuccess = true

                    }.addOnFailureListener { ex ->
                        Log.d(TAG, "addToCart: $ex")
                    }
            task.asDeferred().await()
            return isSuccess
        } else {
            return false
        }


    }

    suspend fun removeCartItem(menuItem: MenuItem): Boolean {
        val currentUser: User? = dataSource.currentUser
        var isSuccess: Boolean = false

        if (currentUser != null) {
            val task: Task<Void> =
                db.collection(COLLECTION_NAME).document(currentUser!!.id)
                    .update("cartItemIds", FieldValue.arrayRemove(menuItem.id))
                    .addOnSuccessListener { doc ->
                        Log.d(TAG, "addToCart: Successfully removed item from cart on firestore ")
                        isSuccess = true
                        dataSource.userCartList.remove(menuItem)
                        clickListener.onCartItemsChangedListener()
                    }.addOnFailureListener { ex ->
                        Log.d(TAG, "removeFromCart: $ex")
                    }
            task.asDeferred().await()
            return isSuccess
        } else {
            return false
        }
    }

    fun getUsersCart() {
        val currentUser: User? = dataSource.currentUser
        val cartItemsList: MutableList<MenuItem> = mutableListOf()
        try {
            val task: Task<DocumentSnapshot> =
                db.collection(COLLECTION_NAME).document(currentUser!!.id)
                    .get().addOnSuccessListener { document ->
                        val currentUserId = dataSource.currentUser!!.id
                        val user: User =
                            User.fromMap(document.data as Map<String, Any>, currentUserId)
                        val receivedCartItemIds = user.cartItemIds
                        receivedCartItemIds.forEach { itemId ->
                            dataSource.menuItemList.map {
                                if (it.id == itemId) {
                                    cartItemsList += it
                                }
                            }
                        }
                        Log.d(TAG, "cart: ")
                        dataSource.userCartList = ArrayList(cartItemsList)
                        clickListener.onCartItemsChangedListener()
                    }.addOnFailureListener {
                        Log.e(TAG, "getUsersCart: Unable to fetch user object in carts repo")
                    }

        } catch (e: FirebaseException) {
        }
    }
}
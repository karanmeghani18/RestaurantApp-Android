package com.example.projectg12.repository

import android.util.Log
import com.example.projectg12.R
import com.example.projectg12.models.MenuItem
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import java.io.*
import java.lang.Exception

class MenuRepo {
    private val TAG = "MENU_REPO"
    private val db = Firebase.firestore
    private val COLLECTION_NAME = "menuItems"




}
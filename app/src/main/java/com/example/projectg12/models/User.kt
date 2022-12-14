package com.example.projectg12.models

import java.util.UUID

class User(var id: String = UUID.randomUUID().toString(), var name: String="") {
    override fun toString(): String {
        return "User(id='$id', name='$name')"
    }
}
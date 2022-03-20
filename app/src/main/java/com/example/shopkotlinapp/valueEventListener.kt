package com.example.shopkotlinapp

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError

open class valueEventListener {

    open fun onDataChange(snapshot: DataSnapshot) {}
    open fun onCancelled(error: DatabaseError) {}
}

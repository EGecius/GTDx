package com.egecius.gtdx.db

import android.util.Log

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

/**
 * Top abstraction level for my database
 */
// TODO: 05/02/2017 to be removed later
class DatabaseExploration {
    private var dbRefRoot: DatabaseReference? = null
    private var db: FirebaseDatabase? = null

    init {
        init()
    }

    private fun init() {
        db = FirebaseDatabase.getInstance()
        dbRefRoot = db!!.reference
    }


    private fun setListener(messagesRef: DatabaseReference) {
        messagesRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                Log.i("Eg:DatabaseExploration:111", "onDataChange dataSnapshot " + dataSnapshot)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.e("Eg:DatabaseExploration:116", "onCancelled databaseError " + databaseError)
            }
        })
    }


    fun deleteMessage() {


        val messageRef = dbRefRoot!!.child("message")

        messageRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                Log.i("Eg:DatabaseExploration:71", "onDataChange dataSnapshot.getValue() " + dataSnapshot.value)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.e("Eg:DatabaseExploration:76", "onCancelled databaseError " + databaseError)
            }
        })

        messageRef.removeValue()
    }

    companion object {

        private val KEY_MESSAGE = "message"
    }

}

package com.example.oyunmerkezi.ui.main

import android.util.Log
import com.example.oyunmerkezi.util.FirebaseGame
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase

class GameRepository() {//(private val database: GameDao,private val context: Context) {

    private val firebaseDatabase = FirebaseDatabase.getInstance()
    private val myRef = firebaseDatabase.getReference("platforms/PS3")

    fun getGame(gameId: String, onResult: (FirebaseGame?) -> Unit) {
        myRef.child(gameId).get().addOnSuccessListener { snapshot ->
            val game = snapshot.getValue(FirebaseGame::class.java)
            onResult(game) // Return data to ViewModel
        }.addOnFailureListener {
            Log.e("Firebase", "Failed to fetch data", it)
            onResult(null)
        }
        myRef.addChildEventListener(mChildEventListener)
        myRef.keepSynced(true)
    }

        private val mChildEventListener = object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, s: String?) {

                val game = dataSnapshot.getValue(FirebaseGame::class.java)!!
                if (game != null) {

                    Log.d("Firebase", "User Namee2: ${game.gameName}, Age: ${game.gameId}")
                } else {
                    Log.e("Firebase", "User namee2e not found")
                }
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                TODO("Not yet implemented")
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        }

    }

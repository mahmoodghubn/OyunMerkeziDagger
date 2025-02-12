package com.example.oyunmerkezi.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.oyunmerkezi.util.FirebaseGame
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class GameRepository() {//(private val database: GameDao,private val context: Context) {

    private val database =
        FirebaseDatabase.getInstance().getReference("platforms/PS3") // Correct path

    private val _games = MutableLiveData<List<FirebaseGame>>()
    val games: LiveData<List<FirebaseGame>> get() = _games

    fun fetchGames() {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val gameList = mutableListOf<FirebaseGame>()
                for (gameSnapshot in snapshot.children) {
                    val game = gameSnapshot.getValue(FirebaseGame::class.java)
                    game?.let { gameList.add(it) }
                }
                _games.value = gameList
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("GameRepository", "Database error: ${error.message}")
            }
        })
    }
}


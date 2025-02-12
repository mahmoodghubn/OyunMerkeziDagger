package com.example.oyunmerkezi.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.oyunmerkezi.util.FirebaseGame
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.launch
import javax.inject.Inject

class GameViewModel @Inject constructor() : ViewModel() {

    private val repository = GameRepository() // Use repository

    val games: LiveData<List<FirebaseGame>> get() = repository.games

    init {
        repository.fetchGames() // Call repository method
    }



}
package com.example.oyunmerkezi.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.oyunmerkezi.util.FirebaseGame
import com.example.oyunmerkezi.util.Game

class GameViewModel : ViewModel() {

    private val repository = GameRepository()

    private val _game = MutableLiveData<FirebaseGame?>()
    val game: LiveData<FirebaseGame?> get() = _game

    fun fetchGame(gameId: String) {
        repository.getGame(gameId) { fetchedGame ->
            _game.value = fetchedGame
        }
    }
}
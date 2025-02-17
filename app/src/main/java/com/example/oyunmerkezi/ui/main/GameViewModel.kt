package com.example.oyunmerkezi.ui.main

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.oyunmerkezi.database.GameDao
import com.example.oyunmerkezi.util.FirebaseGame
import javax.inject.Inject

class GameViewModel @Inject constructor( val gameDao: GameDao,
                                         val dataStore: DataStore<Preferences>,
                                         val application: Application
) : ViewModel() {

    private val repository = GameRepository(gameDao,dataStore,application) // Use repository

    val games: LiveData<List<FirebaseGame>> get() = repository.games

    init {
        repository.fetchGames() // Call repository method
        repository.downloadAll()
    }



}
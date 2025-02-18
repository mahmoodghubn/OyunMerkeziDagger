package com.example.oyunmerkezi.ui.main

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.asLiveData
import com.example.oyunmerkezi.database.GameDao
import javax.inject.Inject
import com.example.oyunmerkezi.util.Game
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class GameViewModel @Inject constructor(private val gameDao: GameDao,
                                        dataStore: DataStore<Preferences>,
                                        val application: Application
) : ViewModel() {

    private val repository = GameRepository(gameDao,dataStore,application) // Use repository

    private val currentPlatformFlow: Flow<String> = dataStore.data.map { preferences ->
        preferences[stringPreferencesKey("current")] ?: "PS4"
    }

    val games: LiveData<List<Game>?> = currentPlatformFlow.flatMapLatest { currentPlatform ->
        // Convert LiveData to Flow using flow builder
        flow {
            val liveData = gameDao.getPlatform(currentPlatform)
            emitAll(liveData.asFlow()) // Use emitAll to emit all values from LiveData
        }
    }.asLiveData() // Convert the Flow to LiveData

    init {
        repository.downloadAll() // Call repository method
    }


}
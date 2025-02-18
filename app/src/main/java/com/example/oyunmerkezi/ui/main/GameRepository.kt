package com.example.oyunmerkezi.ui.main

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.oyunmerkezi.R
import com.example.oyunmerkezi.database.GameDao
import com.example.oyunmerkezi.database.Utils
import com.example.oyunmerkezi.util.FirebaseGame
import com.example.oyunmerkezi.util.Game
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GameRepository constructor(
    private val gameDao: GameDao,
    private val dataStore: DataStore<Preferences>,
    private val application: Application
) {

    private val activeListeners = mutableMapOf<DatabaseReference, ChildEventListener>()
    private var job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + job)
    fun downloadAll() {

        val mChildEventListener = object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, s: String?) {
                dataSnapshot.getValue(FirebaseGame::class.java)?.let { game ->
                    uiScope.launch {
                        if (getGame(game.gameId) == null) {
                            insertGame(Game(game))
                            //TODO show notification on new game added
                        }
                    }
                }
            }

            override fun onChildChanged(dataSnapshot: DataSnapshot, s: String?) {
                val firebaseGame = dataSnapshot.getValue(FirebaseGame::class.java)!!
                uiScope.launch {
                    val databaseGame = getGame(firebaseGame.gameId)!!
                    updateGame(
                        Game(
                            firebaseGame,
                            databaseGame.showNotification,
                            databaseGame.favorite
                        )
                    )
                    //TODO make notification on change happens on game
                    //TODO make sure the changes is only on the price or stock

                }
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
        val platformsArray: Array<String> = application.resources.getStringArray(R.array.platforms)

        val activePlatforms: Flow<Map<String, Boolean>> = dataStore.data.map { preferences ->
            platformsArray.associateWith { key ->
                preferences[stringPreferencesKey(key)] as? Boolean ?: true
            }
        }
        uiScope.launch {
            activePlatforms.collect { platformMap ->
                platformMap.forEach { (platform, isEnabled) ->
                    val platformRef = Utils.databaseRef?.child("platforms")?.child(platform)

                    if (platformRef != null) {
                        if (isEnabled) {
                            // Remove existing listener before adding a new one
                            activeListeners[platformRef]?.let {
                                platformRef.removeEventListener(it)
                            }

                            platformRef.keepSynced(true)
                            platformRef.addChildEventListener(mChildEventListener)

                            activeListeners[platformRef] = mChildEventListener // Track new listener
                        } else {
                            // If platform is disabled, remove its listener
                            activeListeners[platformRef]?.let {
                                platformRef.removeEventListener(it)
                                activeListeners.remove(platformRef)
                            }
                        }
                    }
                }
            }
        }

    }

    private fun insertGame(game: Game) {
        uiScope.launch {
            insert(game)
        }
    }

    private suspend fun insert(game: Game) {
        withContext(Dispatchers.IO) {
            gameDao.upsert(game)
        }

    }

    private suspend fun getGame(gameId: Long): Game? {
        return gameDao.get(gameId)
    }

    private fun updateGame(game: Game) {
        uiScope.launch {
            update(game)
        }
    }

    private suspend fun update(game: Game) {
        withContext(Dispatchers.IO) {
            gameDao.update(game)
        }
    }


    private fun deleteGame(game: Game) {
        uiScope.launch {
            delete(game)
        }
    }

    private suspend fun delete(game: Game) {
        withContext(Dispatchers.IO) {
            gameDao.delete(game)
        }
    }
}

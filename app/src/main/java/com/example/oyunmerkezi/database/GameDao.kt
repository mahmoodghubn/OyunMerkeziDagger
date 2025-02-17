package com.example.oyunmerkezi.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.oyunmerkezi.util.Game

@Dao
interface GameDao {

    @Upsert
    fun upsert (game: Game)

    @Update
    fun update(game:Game)

    @Delete
    fun delete(game: Game)

    @Query("SELECT * from game_table WHERE gameId = :key")
    suspend fun get(key: Long):Game?

    @Query("SELECT * from game_table WHERE platform = :platform")
    fun getPlatform(platform: String):LiveData<List<Game>?>

    @Query("DELETE from game_table WHERE platform = :platform")
    fun deletePlatform(platform: String)

//    @Query("SELECT * FROM game_table WHERE favorite = :boolean")
//    fun getAllFavorite(boolean: Boolean): LiveData<List<Game>?>


    @Query("SELECT * FROM game_table")
    fun getAll(): LiveData<List<Game>?>

}
package com.example.oyunmerkezi.util

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game_table")
data class FirebaseGame(
    @PrimaryKey(autoGenerate = true)
    var gameId: Long = 0L,

    @ColumnInfo
    var gameName: String = "",

    @ColumnInfo
    var sellingPrice: Int = 0,

    @ColumnInfo
    var buyingPrice: Int = 0,

    @ColumnInfo
    var URL: List<String>,

    @ColumnInfo
    var about: String = "",

    @ColumnInfo
    var age: Int = 3,

    @ColumnInfo
    var gameRating: List<Int>,

    @ColumnInfo
    var stock: Boolean = false,

    @ColumnInfo
    var hours: Int = 1,

    @ColumnInfo
    var publishedDate: Date = Date(1990, 1, 1),

    @ColumnInfo
    var online: Online,

    @ColumnInfo
    var category: Category,

    @ColumnInfo
    var platform: Platform,

    @ColumnInfo
    var playerNo: List<Int>,

    @ColumnInfo
    var language: List<Language>,

    @ColumnInfo
    var caption: List<Language>
)  {
    constructor() : this(
        0,
        "",
        0,
        0,
        emptyList(),
        "",
        3,
        listOf(),
        false,
        1,
        Date(1990, 1, 1),
        Online.Online,
        Category.Race,
        Platform.PS4,
        emptyList(),
        listOf(Language.Turkish, Language.English),
        listOf(Language.Turkish, Language.English)
    )
}


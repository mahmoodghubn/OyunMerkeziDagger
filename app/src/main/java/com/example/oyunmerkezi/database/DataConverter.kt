package com.example.oyunmerkezi.database

import androidx.room.TypeConverter
import com.example.oyunmerkezi.util.Category
import com.example.oyunmerkezi.util.Date
import com.example.oyunmerkezi.util.Language
import com.example.oyunmerkezi.util.Online
import com.example.oyunmerkezi.util.Platform

object DataConverter {
//
//    @TypeConverter
//    @JvmStatic
//    fun fromBoolean(value: Boolean): Int {
//        return if (value) 1 else 0
//    }
//
//    @TypeConverter
//    @JvmStatic
//    fun toBoolean(value: Int): Boolean {
//        return value == 1
//    }

    @TypeConverter
    @JvmStatic
    fun toList(strings: String): List<String> {
        val list = mutableListOf<String>()
        val array = strings.split(",")
        for (s in array) {
            list.add(s)
        }
        return list
    }

    @TypeConverter
    @JvmStatic
    fun toString(strings: List<String>): String {
        var result = ""
        strings.forEachIndexed { index, element ->
            result += element
            if (index != (strings.size - 1)) {
                result += ","
            }
        }
        return result
    }

    @TypeConverter
    @JvmStatic
    fun fromStringToDate(value: String): Date {
        val array = value.split(",")
        return Date(array[0].toInt(), array[1].toInt(), array[2].toInt())
    }

    @TypeConverter
    @JvmStatic
    fun fromDateToString(date: Date): String {
        return "${date.year},${date.month},${date.day}"
    }


    @TypeConverter
    @JvmStatic
    fun onlineToString(date: Online?): String {
        return date!!.name
    }

    @TypeConverter
    @JvmStatic
    fun fromStringToOnline(value: String): Online? {
        return Online.values().find { it.name == value }
    }

    @TypeConverter
    @JvmStatic
    fun platformToString(date: Platform?): String {
        return date!!.name
    }

    @TypeConverter
    @JvmStatic
    fun fromStringToPlatform(value: String): Platform? {
        return Platform.values().find { it.name == value }
    }

    @TypeConverter
    @JvmStatic
    fun categoryToString(date: Category?): String {
        return date!!.name
    }

    @TypeConverter
    @JvmStatic
    fun fromStringToCategory(value: String): Category? {
        return Category.values().find { it.name == value }
    }

    @TypeConverter
    @JvmStatic
    fun languageToString(languages: List<Language>?): String {
        var result = ""
        languages!!.forEachIndexed { index, element ->
            result += element.name
            if (index != (languages.size - 1)) {
                result += ","
            }
        }
        return result
    }

    @TypeConverter
    @JvmStatic
    fun fromStringToLanguage(strings: String): List<Language>? {
        val list = mutableListOf<Language>()
        val array = strings.split(",")
        for (s in array) {
            list.add(Language.values().find { it.name == s }!!)
        }
        return list
    }

    @TypeConverter
    @JvmStatic
    fun toIntList(strings: String): List<Int> {
        val list = mutableListOf<Int>()
        val array = strings.split(",")
        for (item in array) {
            list.add(item.toInt())
        }
        return list
    }

    @TypeConverter
    @JvmStatic
    fun fromIntListToString(strings: List<Int>): String {
        var result = ""
        strings.forEachIndexed { index, element ->
            result += element
            if (index != (strings.size - 1)) {
                result += ","
            }
        }
        return result
    }
}

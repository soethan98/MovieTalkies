package com.example.soe_than.movietalkies.data.local

import android.arch.persistence.room.TypeConverter
import android.util.Log
import com.google.gson.reflect.TypeToken
import com.google.gson.Gson
import java.util.*


class Converters {

    @TypeConverter
    fun listToJson(value: List<Int>?): String {

        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToList(value: String): List<Int>? {

        val objects = Gson().fromJson(value, Array<Int>::class.java) as Array<Int>
        val list = objects.toList()
        return list
    }
}
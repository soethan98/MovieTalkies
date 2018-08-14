package com.example.soe_than.movietalkies.data.local

import android.arch.persistence.room.TypeConverter
import android.util.Log
import com.google.gson.reflect.TypeToken
import com.google.gson.Gson
import java.util.*


object ListGenresConverter {


    @TypeConverter
    @JvmStatic
    fun fromArrayList(list: List<String>): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    @JvmStatic
    fun fromString(value: String): List<String> {
        val listType = object : TypeToken<ArrayList<String>>() {}.type
        return Gson().fromJson(value, listType)
    }



}
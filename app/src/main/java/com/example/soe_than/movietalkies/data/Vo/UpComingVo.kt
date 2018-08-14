package com.example.soe_than.movietalkies.data.Vo

import android.arch.persistence.room.*
import android.os.Parcel
import android.os.Parcelable
import com.example.soe_than.movietalkies.data.local.ListGenresConverter
import com.google.gson.annotations.SerializedName
import java.lang.reflect.GenericArrayType


@Entity(tableName = "upcoming")
data class UpComingVo(
        @PrimaryKey
        @SerializedName("vote_count")
        var vote_count: Int,
        @SerializedName("id")
        var id: Int,
        @SerializedName("vote_average")
        var vote_average: Double,
        @SerializedName("title")
        var title: String,
        @SerializedName("popularity")
        var popularity: Double,
        @SerializedName("poster_path")
        var poster_path: String,
        @SerializedName("original_language")
        var original_language: String,
        @SerializedName("original_title")
        var original_title: String,
        @SerializedName("genre_ids")
        val genreids: List<String>,
        @SerializedName("backdrop_path")
        var backdrop_path: String,
        @SerializedName("overview")
        var overview: String,
        @SerializedName("release_date")
        var release_date: String)

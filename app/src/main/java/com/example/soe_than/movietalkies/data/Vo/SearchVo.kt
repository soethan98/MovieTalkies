package com.example.soe_than.movietalkies.data.Vo

import android.os.Parcel
import android.os.Parcelable
import com.bumptech.glide.load.engine.bitmap_recycle.IntegerArrayAdapter
import com.google.gson.annotations.SerializedName

data class SearchVo(
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
        var genreids: List<String> = ArrayList<String>(),
        @SerializedName("backdrop_path")
        var backdrop_path: String,
        @SerializedName("overview")
        var overview: String,
        @SerializedName("release_date")
        var release_date: String) {}
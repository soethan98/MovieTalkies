package com.soethan.movietalkies.data.Vo

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
        var genreids: List<Int> ,
        @SerializedName("backdrop_path")
        var backdrop_path: String,
        @SerializedName("overview")
        var overview: String,
        @SerializedName("release_date")
        var release_date: String) {}
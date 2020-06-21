package com.example.soe_than.movietalkies.data.Vo

import com.google.gson.annotations.SerializedName

data class MovieDetailVo(
    @SerializedName("backdrop_path")
    var backdrop_path: String,
    @SerializedName("genres")
    var genreids: List<GenresVo> = ArrayList<GenresVo>(),
    @SerializedName("id")
    var id: Int,
    @SerializedName("original_language")
    var original_language: String,
    @SerializedName("original_title")
    var original_title: String,
    @SerializedName("overview")
    var overview: String,
    @SerializedName("popularity")
    var popularity: Double,
    @SerializedName("poster_path")
    var poster_path: String,
    @SerializedName("release_date")
    var release_date: String,
    @SerializedName("runtine")
    var runtime: Int,
    @SerializedName("title")
    var title: String,
    @SerializedName("vote_average")
    var vote_average: Double
)

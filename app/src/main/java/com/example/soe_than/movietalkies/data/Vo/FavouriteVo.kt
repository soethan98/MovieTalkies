package com.example.soe_than.movietalkies.data.Vo

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "favourite")
data class FavouriteVo(@PrimaryKey
                       var id: Int = 0,
                       @ColumnInfo(name = "poster_path")
                       var posterPath: String? = null,
                       @ColumnInfo(name = "overview")
                       var overview: String? = null,
                       @ColumnInfo(name = "original_title")
                       var title: String? = null,
                       @ColumnInfo(name = "release_date")
                       var releasedDate: String? = null,
                       @ColumnInfo(name = "vote_average")
                       var voteAverage: Double = 0.toDouble(),
                       @ColumnInfo(name = "backdrop_path")
                       var backdrop_path: String? = null,
                       @ColumnInfo(name = "genres_ids")
                       val genreids: List<Int>)
package com.example.soe_than.movietalkies.data.Vo

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity (tableName = "toprated")
data class TopRatedVo( @PrimaryKey
                       @SerializedName("id")
                       var id: Int = 0,

                       @ColumnInfo(name = "poster_path")
                       @SerializedName("poster_path")
                       var posterPath: String? = null,

                       @ColumnInfo(name = "overview")
                       @SerializedName("overview")
                       var overview: String? = null,

                       @ColumnInfo(name = "original_title")
                       @SerializedName("original_title")
                       var title: String? = null,

                       @ColumnInfo(name = "release_date")
                       @SerializedName("release_date")
                       var releasedDate: String? = null,

                       @ColumnInfo(name = "vote_average")
                       @SerializedName("vote_average")
                       var voteAverage: Double = 0.toDouble(),


                       @ColumnInfo(name = "backdrop_path")
                       @SerializedName("backdrop_path")
                       var backdrop_path: String? = null) {
}
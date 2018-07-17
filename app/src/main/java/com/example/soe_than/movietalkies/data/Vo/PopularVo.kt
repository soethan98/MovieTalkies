package com.example.soe_than.movietalkies.data.Vo

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Entity (tableName = "popular")
data class PopularVo ( @PrimaryKey
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
                       var backdrop_path: String? = null):Parcelable{
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readDouble(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(posterPath)
        parcel.writeString(overview)
        parcel.writeString(title)
        parcel.writeString(releasedDate)
        parcel.writeDouble(voteAverage)
        parcel.writeString(backdrop_path)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PopularVo> {
        override fun createFromParcel(parcel: Parcel): PopularVo {
            return PopularVo(parcel)
        }

        override fun newArray(size: Int): Array<PopularVo?> {
            return arrayOfNulls(size)
        }
    }
}
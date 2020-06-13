package com.example.soe_than.movietalkies.data.local

import androidx.room.*
import android.content.Context
import com.example.soe_than.movietalkies.data.Vo.*
import com.example.soe_than.movietalkies.data.local.Daos.*


@Database(entities = [NowShowingVo::class, UpComingVo::class, PopularVo::class, TopRatedVo::class, FavouriteVo::class], version = 1)
@TypeConverters(Converters::class)
abstract class MovieDatabase : RoomDatabase() {



    abstract fun movieDao(): MovieDao


}
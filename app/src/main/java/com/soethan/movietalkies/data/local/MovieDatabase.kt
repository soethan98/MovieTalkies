package com.soethan.movietalkies.data.local

import androidx.room.*
import com.soethan.movietalkies.data.Vo.*
import com.soethan.movietalkies.data.local.Daos.*


@Database(entities = [NowShowingVo::class, UpComingVo::class, PopularVo::class, TopRatedVo::class, FavouriteVo::class], version = 1)
@TypeConverters(Converters::class)
abstract class MovieDatabase : RoomDatabase() {



    abstract fun movieDao(): MovieDao


}
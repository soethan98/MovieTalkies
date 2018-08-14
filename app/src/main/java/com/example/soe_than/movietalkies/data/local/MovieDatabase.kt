package com.example.soe_than.movietalkies.data.local

import android.arch.persistence.room.*
import android.content.Context
import com.example.soe_than.movietalkies.data.Vo.*
import com.example.soe_than.movietalkies.data.local.Daos.*


@Database(entities = arrayOf(NowShowingVo::class, UpComingVo::class,
        PopularVo::class, TopRatedVo::class, FavouriteVo::class), version = 1)
@TypeConverters(ListGenresConverter::class)
abstract class MovieDatabase : RoomDatabase() {

//    abstract fun popularDao(): PopularDao
//    abstract fun upcomingDao():UpComingDao
//    abstract fun nowshowingDao():NowShowingDao
//    abstract fun topratedDao():TopRatedDao


    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile
        private var INSTANCE: MovieDatabase? = null


        fun getInstance(context: Context): MovieDatabase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildDatabase(context).also { INSTANCE = it }

        }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                        MovieDatabase::class.java, "Movies.db")
                        .build()

    }
}
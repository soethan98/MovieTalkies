package com.example.soe_than.movietalkies.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.soe_than.movietalkies.data.Vo.*
import com.example.soe_than.movietalkies.data.local.Daos.*


@Database(entities = arrayOf(NowShowingVo::class,UpComingVo::class,
        PopularVo::class,TopRatedVo::class,FavouriteVo::class), version = 1)
abstract class MovieDatabase:RoomDatabase() {

    abstract fun popularDao(): PopularDao
    abstract fun upcomingDao():UpComingDao
    abstract fun nowshowingDao():NowShowingDao
    abstract fun topratedDao():TopRatedDao


    abstract fun movieDao():MovieDao

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
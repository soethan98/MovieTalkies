package com.example.soe_than.movietalkies.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.soe_than.movietalkies.data.Vo.NowShowingVo
import com.example.soe_than.movietalkies.data.Vo.PopularVo
import com.example.soe_than.movietalkies.data.Vo.TopRatedVo
import com.example.soe_than.movietalkies.data.Vo.UpComingVo
import com.example.soe_than.movietalkies.data.local.Daos.NowShowingDao
import com.example.soe_than.movietalkies.data.local.Daos.PopularDao
import com.example.soe_than.movietalkies.data.local.Daos.TopRatedDao
import com.example.soe_than.movietalkies.data.local.Daos.UpComingDao


@Database(entities = arrayOf(NowShowingVo::class,UpComingVo::class,
        PopularVo::class,TopRatedVo::class), version = 1)
abstract class MovieDatabase:RoomDatabase() {

    abstract fun popularDao(): PopularDao
    abstract fun upcomingDao():UpComingDao
    abstract fun nowshowingDao():NowShowingDao
    abstract fun topratedDao():TopRatedDao

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
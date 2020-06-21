package com.example.soe_than.movietalkies.di.module

import android.app.Application
import androidx.room.Room
import com.example.soe_than.movietalkies.data.local.Daos.MovieDao
import com.example.soe_than.movietalkies.data.local.MovieDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DbModule {

    @Singleton
    @Provides
    fun provideMovieDb(app: Application): MovieDatabase {

        return Room
                .databaseBuilder(app, MovieDatabase::class.java, "Movies.db")
                .build()
    }

    @Singleton
    @Provides
    fun provideMovieDao(movieDatabase: MovieDatabase): MovieDao {

        return movieDatabase.movieDao()
    }
}

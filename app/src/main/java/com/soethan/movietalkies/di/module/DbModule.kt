package com.soethan.movietalkies.di.module

import android.app.Application
import androidx.room.Room
import com.soethan.movietalkies.data.local.Daos.MovieDao
import com.soethan.movietalkies.data.local.MovieDatabase
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
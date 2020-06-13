package com.example.soe_than.movietalkies.Utils

import android.content.Context
import com.example.soe_than.movietalkies.api.ApiService
import com.example.soe_than.movietalkies.data.local.MovieDatabase
import com.example.soe_than.movietalkies.data.repository.*
import com.example.soe_than.movietalkies.ui.ViewModelFactory.*


object InjectorUtils {


//    private fun provideMoviesRepository(context: Context): MoviesRepository? {
//        val database = MovieDatabase.getInstance(context)
//        val apiService = ApiService.create()
//        return MoviesRepository(database.movieDao(), apiService, context)
//    }
//
//    fun provideFavouriteViewFactory(context: Context): FavouriteViewFactory {
//        val moviesRepository = provideMoviesRepository(context)
//        return FavouriteViewFactory(moviesRepository!!)
//    }
//
//    fun provideMovieViewModelFactory(context: Context): MovieViewModelFactory {
//        val moviesRepository = provideMoviesRepository(context)
//        return MovieViewModelFactory(moviesRepository!!)
//    }
//
//    fun provideDetailViewFactory(context: Context, id: Int): DetailViewModelFactory {
//        val moviesRepository = provideMoviesRepository(context)
//        return DetailViewModelFactory(moviesRepository!!, id)
//    }
//
//    fun provideSearchDetailViewFactory(context: Context, id: Int): SearchDetailViewModelFactory {
//        val moviesRepository = provideMoviesRepository(context)
//        return SearchDetailViewModelFactory(moviesRepository!!, id)
//    }
}
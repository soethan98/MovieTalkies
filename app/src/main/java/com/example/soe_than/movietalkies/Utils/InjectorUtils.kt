package com.example.soe_than.movietalkies.Utils

import android.content.Context
import com.example.soe_than.movietalkies.data.Vo.PopularVo
import com.example.soe_than.movietalkies.data.local.Daos.NowShowingDao
import com.example.soe_than.movietalkies.data.local.Daos.PopularDao
import com.example.soe_than.movietalkies.data.local.Daos.TopRatedDao
import com.example.soe_than.movietalkies.data.local.Daos.UpComingDao
import com.example.soe_than.movietalkies.data.local.MovieDatabase
import com.example.soe_than.movietalkies.data.repository.*
import com.example.soe_than.movietalkies.ui.ViewModelFactory.*


object InjectorUtils {


    fun provideMoviesRepository(context: Context): MoviesRepository? {
        val database = MovieDatabase.getInstance(context)
        return MoviesRepository.getInstance(database.movieDao(), context)
    }


    fun provideUpComingViewFactory(context: Context): UpComingViewFactory {
        val moviesRepository = provideMoviesRepository(context)
        return UpComingViewFactory(moviesRepository!!)

    }

    fun provideFavouriteViewFactory(context: Context): FavouriteViewFactory {
        val moviesRepository = provideMoviesRepository(context)
        return FavouriteViewFactory(moviesRepository!!)
    }

    fun provideNowShowingViewFactory(context: Context): NowShowingViewFactory {
        val movieRepository = provideMoviesRepository(context)
        return NowShowingViewFactory(movieRepository!!)

    }

    fun providePopularViewFactory(context: Context): PopularViewFactory {
        val moviesRepository = provideMoviesRepository(context)
        return PopularViewFactory(moviesRepository!!)

    }

    fun provideTopRatedViewFactory(context: Context): TopRatedViewFactory {
        val moviesRepository = provideMoviesRepository(context)
        return TopRatedViewFactory(moviesRepository!!)
    }

    fun provideDetailViewFactory(context: Context, id: Int): DetailViewModelFactory {
        val moviesRepository = provideMoviesRepository(context)
        return DetailViewModelFactory(moviesRepository!!, id)
    }


}
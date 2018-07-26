package com.example.soe_than.movietalkies.Utils

import android.content.Context
import com.example.soe_than.movietalkies.data.Vo.PopularVo
import com.example.soe_than.movietalkies.data.local.Daos.NowShowingDao
import com.example.soe_than.movietalkies.data.local.Daos.PopularDao
import com.example.soe_than.movietalkies.data.local.Daos.TopRatedDao
import com.example.soe_than.movietalkies.data.local.Daos.UpComingDao
import com.example.soe_than.movietalkies.data.local.MovieDatabase
import com.example.soe_than.movietalkies.data.repository.*
import com.example.soe_than.movietalkies.ui.ViewModelFactory.NowShowingViewFactory
import com.example.soe_than.movietalkies.ui.ViewModelFactory.PopularViewFactory
import com.example.soe_than.movietalkies.ui.ViewModelFactory.TopRatedViewFactory
import com.example.soe_than.movietalkies.ui.ViewModelFactory.UpComingViewFactory
import com.example.soe_than.movietalkies.ui.ViewModelFactory.DetailViewModelFactory



object InjectorUtils {


//    fun providePopularRepository(context: Context): PopularRepository? {
//        val database = MovieDatabase.getInstance(context)
//        return PopularRepository.getInstance(database.popularDao())
//    }

    fun provideMoviesRepository(context: Context):MoviesRepository?
    {
        val database = MovieDatabase.getInstance(context)
        return MoviesRepository.getInstance(database.movieDao(),context)
    }

//    fun provideTopRatedRepository(context: Context): TopRatedRepository? {
//        val database = MovieDatabase.getInstance(context)
//        return TopRatedRepository.getInstance(database.topratedDao())
//    }
//
//    fun provideUpComingRepository(context: Context): UpComingRepository? {
//        val database = MovieDatabase.getInstance(context)
//        return UpComingRepository.getInstance(database.upcomingDao())
//    }
//
//    fun provideNowShowingRepository(context: Context): NowShowingRepository? {
//        val database = MovieDatabase.getInstance(context)
//        return NowShowingRepository.getInstance(database.nowshowingDao())
//    }

    fun provideUpComingViewFactory(context: Context): UpComingViewFactory {
        val moviesRepository = provideMoviesRepository(context)
        return UpComingViewFactory(moviesRepository!!)

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

    fun provideDetailViewFactory(context: Context,id:Int):DetailViewModelFactory
    {
        val moviesRepository = provideMoviesRepository(context)
        return DetailViewModelFactory(moviesRepository!!,id)
    }



}
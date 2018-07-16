package com.example.soe_than.movietalkies.Utils

import android.content.Context
import com.example.soe_than.movietalkies.data.local.Daos.NowShowingDao
import com.example.soe_than.movietalkies.data.local.Daos.PopularDao
import com.example.soe_than.movietalkies.data.local.Daos.TopRatedDao
import com.example.soe_than.movietalkies.data.local.Daos.UpComingDao
import com.example.soe_than.movietalkies.data.local.MovieDatabase
import com.example.soe_than.movietalkies.data.repository.NowShowingRepository
import com.example.soe_than.movietalkies.data.repository.PopularRepository
import com.example.soe_than.movietalkies.data.repository.TopRatedRepository
import com.example.soe_than.movietalkies.data.repository.UpComingRepository
import com.example.soe_than.movietalkies.ui.ViewModelFactory.NowShowingViewFactory
import com.example.soe_than.movietalkies.ui.ViewModelFactory.PopularViewFactory
import com.example.soe_than.movietalkies.ui.ViewModelFactory.TopRatedViewFactory
import com.example.soe_than.movietalkies.ui.ViewModelFactory.UpComingViewFactory

object InjectorUtils {


    fun providePopularRepository(context: Context): PopularRepository? {
        val database = MovieDatabase.getInstance(context)
        return PopularRepository.getInstance(database.popularDao())
    }

    fun provideTopRatedRepository(context: Context): TopRatedRepository? {
        val database = MovieDatabase.getInstance(context)
        return TopRatedRepository.getInstance(database.topratedDao())
    }

    fun provideUpComingRepository(context: Context): UpComingRepository? {
        val database = MovieDatabase.getInstance(context)
        return UpComingRepository.getInstance(database.upcomingDao())
    }

    fun provideNowShowingRepository(context: Context): NowShowingRepository? {
        val database = MovieDatabase.getInstance(context)
        return NowShowingRepository.getInstance(database.nowshowingDao())
    }

    fun provideUpComingViewFactory(context: Context): UpComingViewFactory {
        val upcomingRepository = provideUpComingRepository(context)
        return UpComingViewFactory(upcomingRepository!!)

    }

    fun provideNowShowingViewFactory(context: Context): NowShowingViewFactory {
        val nowShowingRepository = provideNowShowingRepository(context)
        return NowShowingViewFactory(nowShowingRepository!!)

    }

    fun providePopularViewFactory(context: Context): PopularViewFactory {
        val popularRepository = providePopularRepository(context)
        return PopularViewFactory(popularRepository!!)

    }

    fun provideTopRatedViewFactory(context: Context): TopRatedViewFactory {
        val topRatedRepository = provideTopRatedRepository(context)
        return TopRatedViewFactory(topRatedRepository!!)

    }


}
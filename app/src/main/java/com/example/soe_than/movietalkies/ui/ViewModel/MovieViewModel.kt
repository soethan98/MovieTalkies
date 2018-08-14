package com.example.soe_than.movietalkies.ui.ViewModel

import android.app.DownloadManager
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.example.soe_than.movietalkies.data.Vo.*
import com.example.soe_than.movietalkies.data.repository.MoviesRepository

class MovieViewModel(val moviesRepository: MoviesRepository) : ViewModel() {

    fun getSearchMovie(query: String): LiveData<List<SearchVo>> {
        return moviesRepository.getSearchList(query)
    }


    fun getNowShowingMovies(): LiveData<List<NowShowingVo>> {
        return moviesRepository.getNowShowingMovies()

    }


    fun getPopularMovies(): LiveData<List<PopularVo>> {
        return moviesRepository.getPopularMovies()

    }

    fun getTopRatedMovies(): LiveData<List<TopRatedVo>> {
        return moviesRepository.getTopRatedMovies()

    }

    fun getUpComingMovies(): LiveData<List<UpComingVo>> {
        return moviesRepository.getUpComingMovies()

    }

}
package com.example.soe_than.movietalkies.ui.ViewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.view.View
import com.example.soe_than.movietalkies.data.Vo.*
import com.example.soe_than.movietalkies.data.repository.MoviesRepository

class DetailViewModel(val moviesRepository: MoviesRepository, var id: Int) : ViewModel() {


    fun getPopularMovieDetails(): LiveData<PopularVo> {
        return moviesRepository.getPopularMovieDetails(id)
    }

    fun getNowShowingMovieDetails(): LiveData<NowShowingVo> {
        return moviesRepository.getNowShowingMovieDetail(id)

    }

    fun getTopRatedMovieDetails(): LiveData<TopRatedVo> {
        return moviesRepository.getTopRatedMovieDetails(id)

    }

    fun getUpComingMOvieDetails(): LiveData<UpComingVo> {
        return moviesRepository.getUpComingMovieDetails(id)

    }

    fun getTrailers(): LiveData<List<TrailerVo>>? {
        return moviesRepository.getTrailers(id)
    }


}
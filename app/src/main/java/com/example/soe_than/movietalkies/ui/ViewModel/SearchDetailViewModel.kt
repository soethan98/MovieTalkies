package com.example.soe_than.movietalkies.ui.ViewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.example.soe_than.movietalkies.data.Vo.MovieDetailVo
import com.example.soe_than.movietalkies.data.Vo.TrailerVo
import com.example.soe_than.movietalkies.data.repository.MoviesRepository

class SearchDetailViewModel(val moviesRepository: MoviesRepository, var id: Int) : ViewModel() {

    fun getMovieDetails(): LiveData<MovieDetailVo> {
        return moviesRepository.getSearchMovieDetails(id)
    }

    fun getTrailers(): LiveData<List<TrailerVo>>? {
        return moviesRepository.getTrailers(id)
    }
}
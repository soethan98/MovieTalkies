package com.example.soe_than.movietalkies.ui.ViewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.example.soe_than.movietalkies.data.Vo.UpComingVo
import com.example.soe_than.movietalkies.data.repository.MoviesRepository

class UpComingViewModel(val moviesRepository: MoviesRepository) : ViewModel() {

    fun getUpComingMovies(): LiveData<List<UpComingVo>> {
        return moviesRepository.getUpComingMovies()

    }
}
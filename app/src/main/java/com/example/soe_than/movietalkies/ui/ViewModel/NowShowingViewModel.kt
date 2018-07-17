package com.example.soe_than.movietalkies.ui.ViewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.example.soe_than.movietalkies.data.Vo.NowShowingVo
import com.example.soe_than.movietalkies.data.repository.MoviesRepository
import com.example.soe_than.movietalkies.data.repository.NowShowingRepository

class NowShowingViewModel(val moviesRepository: MoviesRepository) : ViewModel() {


    fun getNowShowingMovies(): LiveData<List<NowShowingVo>> {
        return moviesRepository.getNowShowingMovies()

    }


}
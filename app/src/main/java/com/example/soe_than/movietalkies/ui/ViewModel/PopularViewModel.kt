package com.example.soe_than.movietalkies.ui.ViewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.example.soe_than.movietalkies.data.Vo.NowShowingVo
import com.example.soe_than.movietalkies.data.Vo.PopularVo
import com.example.soe_than.movietalkies.data.repository.MoviesRepository
import com.example.soe_than.movietalkies.data.repository.PopularRepository

class PopularViewModel(val moviesRepository: MoviesRepository):ViewModel(){

    fun getPopularMovies(): LiveData<List<PopularVo>> {
        return moviesRepository.getPopularMovies()

    }
}
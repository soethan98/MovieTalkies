package com.example.soe_than.movietalkies.ui.ViewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.view.View
import com.example.soe_than.movietalkies.data.Vo.NowShowingVo
import com.example.soe_than.movietalkies.data.Vo.PopularVo
import com.example.soe_than.movietalkies.data.Vo.TopRatedVo
import com.example.soe_than.movietalkies.data.Vo.UpComingVo
import com.example.soe_than.movietalkies.data.repository.MoviesRepository

class DetailViewModel<T>(val moviesRepository: MoviesRepository, val vo: T) : ViewModel() {


    init {
        if (vo is PopularVo) {
            getPopularMovieDetails(vo.id)
        } else if (vo is NowShowingVo) {
            getNowShowingMovieDetails(vo.id)

        } else if (vo is TopRatedVo) {
            getTopRatedMovieDetails(vo.id)

        } else if (vo is UpComingVo) {
            getUpComingMOvieDetails(vo.id)

        }
    }

    fun getPopularMovieDetails(id: Int): LiveData<PopularVo> {
        return moviesRepository.getPopularMovieDetails(id)
    }

    fun getNowShowingMovieDetails(id: Int): LiveData<NowShowingVo> {
        return moviesRepository.getNowShowingMovieDetail(id)

    }

    fun getTopRatedMovieDetails(id: Int): LiveData<TopRatedVo> {
        return moviesRepository.getTopRatedMovieDetails(id)

    }

    fun getUpComingMOvieDetails(id: Int): LiveData<UpComingVo> {
        return moviesRepository.getUpComingMovieDetails(id)

    }


}
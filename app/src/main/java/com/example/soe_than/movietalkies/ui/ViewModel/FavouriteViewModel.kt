package com.example.soe_than.movietalkies.ui.ViewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.example.soe_than.movietalkies.data.Vo.FavouriteVo
import com.example.soe_than.movietalkies.data.Vo.NowShowingVo
import com.example.soe_than.movietalkies.data.repository.MoviesRepository

class FavouriteViewModel(val moviesRepository: MoviesRepository) : ViewModel() {

    fun getFavouriteMovies(): LiveData<List<FavouriteVo>> {
        return moviesRepository.getFavourites()

    }

}
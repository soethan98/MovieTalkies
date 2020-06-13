package com.example.soe_than.movietalkies.ui.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.soe_than.movietalkies.data.Vo.FavouriteVo
import com.example.soe_than.movietalkies.data.Vo.NowShowingVo
import com.example.soe_than.movietalkies.data.repository.MoviesRepository
import javax.inject.Inject

class FavouriteViewModel @Inject constructor( val moviesRepository: MoviesRepository) : ViewModel() {

    fun getFavouriteMovies() = moviesRepository.getFavourites()


}
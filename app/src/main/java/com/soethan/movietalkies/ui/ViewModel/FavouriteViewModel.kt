package com.soethan.movietalkies.ui.ViewModel

import androidx.lifecycle.ViewModel
import com.soethan.movietalkies.data.repository.MoviesRepository
import javax.inject.Inject

class FavouriteViewModel @Inject constructor( val moviesRepository: MoviesRepository) : ViewModel() {

    fun getFavouriteMovies() = moviesRepository.getFavourites()


}
package com.example.soe_than.movietalkies.ui.ViewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.soe_than.movietalkies.data.repository.MoviesRepository
import com.example.soe_than.movietalkies.ui.ViewModel.FavouriteViewModel
import com.example.soe_than.movietalkies.ui.ViewModel.MovieViewModel

//class MovieViewModelFactory(val moviesRepository: MoviesRepository) : ViewModelProvider.NewInstanceFactory() {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//
//        return MovieViewModel(moviesRepository) as T
//    }
//}
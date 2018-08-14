package com.example.soe_than.movietalkies.ui.ViewModelFactory

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.soe_than.movietalkies.data.repository.MoviesRepository
import com.example.soe_than.movietalkies.ui.ViewModel.FavouriteViewModel

class FavouriteViewFactory(val moviesRepository: MoviesRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return FavouriteViewModel(moviesRepository) as T
    }

}
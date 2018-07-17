package com.example.soe_than.movietalkies.ui.ViewModelFactory

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.soe_than.movietalkies.data.repository.MoviesRepository
import com.example.soe_than.movietalkies.data.repository.NowShowingRepository
import com.example.soe_than.movietalkies.data.repository.TopRatedRepository
import com.example.soe_than.movietalkies.ui.ViewModel.NowShowingViewModel
import com.example.soe_than.movietalkies.ui.ViewModel.TopRatedViewModel

class TopRatedViewFactory(val moviesRepository: MoviesRepository):ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return TopRatedViewModel(moviesRepository) as T
    }

}
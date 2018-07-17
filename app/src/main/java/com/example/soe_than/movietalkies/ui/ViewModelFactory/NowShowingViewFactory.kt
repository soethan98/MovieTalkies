package com.example.soe_than.movietalkies.ui.ViewModelFactory

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.soe_than.movietalkies.data.local.Daos.NowShowingDao
import com.example.soe_than.movietalkies.data.repository.MoviesRepository
import com.example.soe_than.movietalkies.data.repository.NowShowingRepository
import com.example.soe_than.movietalkies.ui.ViewModel.NowShowingViewModel

class NowShowingViewFactory(val moviesRepository: MoviesRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return NowShowingViewModel(moviesRepository) as T
    }


}
package com.example.soe_than.movietalkies.ui.ViewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.soe_than.movietalkies.data.repository.MoviesRepository
import com.example.soe_than.movietalkies.ui.ViewModel.SearchDetailViewModel

//class SearchDetailViewModelFactory(val moviesRepository: MoviesRepository, var id: Int) : ViewModelProvider.NewInstanceFactory() {
//
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return SearchDetailViewModel(moviesRepository,id) as T
//    }
//}
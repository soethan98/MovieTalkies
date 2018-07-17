package com.example.soe_than.movietalkies.ui.ViewModelFactory

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.soe_than.movietalkies.data.Vo.PopularVo
import com.example.soe_than.movietalkies.data.repository.MoviesRepository
import com.example.soe_than.movietalkies.ui.ViewModel.DetailViewModel
import com.example.soe_than.movietalkies.ui.ViewModel.NowShowingViewModel
import com.example.soe_than.movietalkies.ui.detail.DetailActivity
import java.lang.reflect.Type

class DetailViewModelFactory<T>(val moviesRepository: MoviesRepository,  val vo :T): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return DetailViewModel(moviesRepository,vo) as T
    }
}
package com.example.soe_than.movietalkies.ui.ViewModel

import android.app.DownloadManager
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.example.soe_than.movietalkies.data.Vo.FavouriteVo
import com.example.soe_than.movietalkies.data.Vo.SearchVo
import com.example.soe_than.movietalkies.data.repository.MoviesRepository

class MovieViewModel(val moviesRepository: MoviesRepository) : ViewModel() {

    fun getSearchMovie(query: String): LiveData<List<SearchVo>> {
        return moviesRepository.getSearchList(query)
    }

}
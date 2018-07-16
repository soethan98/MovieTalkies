package com.example.soe_than.movietalkies.ui.ViewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.example.soe_than.movietalkies.data.Vo.PopularVo
import com.example.soe_than.movietalkies.data.Vo.TopRatedVo
import com.example.soe_than.movietalkies.data.repository.TopRatedRepository

class TopRatedViewModel(val topRatedRepository: TopRatedRepository):ViewModel() {

    fun getTopRatedMovies(): LiveData<List<TopRatedVo>> {
        return topRatedRepository.getTopRatedMovies()

    }
}
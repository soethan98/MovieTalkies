package com.example.soe_than.movietalkies.ui.ViewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.example.soe_than.movietalkies.data.Vo.PopularVo
import com.example.soe_than.movietalkies.data.Vo.UpComingVo
import com.example.soe_than.movietalkies.data.repository.UpComingRepository

class UpComingViewModel(val upComingRepository: UpComingRepository) : ViewModel() {

    fun getUpComingMovies(): LiveData<List<UpComingVo>> {
        return upComingRepository.getUpComingMovies()

    }
}
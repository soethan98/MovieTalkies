package com.example.soe_than.movietalkies.ui.ViewModelFactory

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.soe_than.movietalkies.data.local.Daos.NowShowingDao
import com.example.soe_than.movietalkies.data.repository.NowShowingRepository
import com.example.soe_than.movietalkies.ui.ViewModel.NowShowingViewModel

class NowShowingViewFactory(val nowShowingRepository: NowShowingRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return NowShowingViewModel(nowShowingRepository) as T
    }


}
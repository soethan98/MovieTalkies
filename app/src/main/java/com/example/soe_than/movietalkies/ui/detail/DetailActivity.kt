package com.example.soe_than.movietalkies.ui.detail

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.soe_than.movietalkies.R
import com.example.soe_than.movietalkies.Utils.InjectorUtils
import com.example.soe_than.movietalkies.data.Vo.PopularVo
import com.example.soe_than.movietalkies.data.Vo.TopRatedVo
import com.example.soe_than.movietalkies.ui.ViewModel.DetailViewModel
import com.example.soe_than.movietalkies.ui.ViewModel.PopularViewModel
import com.example.soe_than.movietalkies.ui.ViewModelFactory.DetailViewModelFactory
import com.example.soe_than.movietalkies.ui.ViewModelFactory.PopularViewFactory

class DetailActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

       if (intent.getParcelableExtra<PopularVo>("MOVIE") is PopularVo)
       {

             var viewModel: DetailViewModel<PopularVo>
             var viewModelFactory: DetailViewModelFactory<PopularVo>

           viewModelFactory = InjectorUtils.provideDetailViewModelFactory(this)
           viewModel = ViewModelProviders.of(this, viewModelFactory).get(PopularViewModel::class.java)

       }



    }
}

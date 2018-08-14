package com.example.soe_than.movietalkies.ui.detail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.soe_than.movietalkies.R
import com.example.soe_than.movietalkies.Utils.Constants
import com.example.soe_than.movietalkies.Utils.InjectorUtils
import com.example.soe_than.movietalkies.data.Vo.MovieDetailVo
import com.example.soe_than.movietalkies.ui.ViewModel.DetailViewModel
import com.example.soe_than.movietalkies.ui.ViewModel.SearchDetailViewModel
import com.example.soe_than.movietalkies.ui.ViewModelFactory.DetailViewModelFactory
import com.example.soe_than.movietalkies.ui.ViewModelFactory.SearchDetailViewModelFactory
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_search_detail.*
import kotlinx.android.synthetic.main.detail_movies_content.*

class SearchDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: SearchDetailViewModel
    private lateinit var viewModelFactory: SearchDetailViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_detail)
        val  movieId = intent.getIntExtra("Search",0)

        Log.i("SearchDetail","$movieId")

        viewModelFactory = InjectorUtils.provideSearchDetailViewFactory(this, movieId)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SearchDetailViewModel::class.java)

        viewModel.getMovieDetails().observe(this, Observer { movieDetails ->
            if (movieDetails != null) {
                bindMovieDetailsMovie(movieDetails)


            }

        })

    }

    fun bindMovieDetailsMovie(searchDetailVo: MovieDetailVo)
    {


        Picasso.with(this).load("${Constants.BACKDROP_BASE_URL + searchDetailVo.backdrop_path}").into(search_image)
        Picasso.with(this).load("${Constants.IMAGES_BASE_URL + searchDetailVo.poster_path}").transform(RoundedCornersTransformation(18, 4)).into(poster_image);

        movie_title.setText(searchDetailVo.title)
        movie_overview.setText(searchDetailVo.overview)
        movie_release.setText(searchDetailVo.release_date)
    }
}

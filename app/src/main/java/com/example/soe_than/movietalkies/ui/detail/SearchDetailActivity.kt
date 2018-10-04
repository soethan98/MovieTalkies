package com.example.soe_than.movietalkies.ui.detail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.example.soe_than.movietalkies.R
import com.example.soe_than.movietalkies.Utils.Constants
import com.example.soe_than.movietalkies.Utils.InjectorUtils
import com.example.soe_than.movietalkies.Utils.Utility
import com.example.soe_than.movietalkies.data.Vo.MovieDetailVo
import com.example.soe_than.movietalkies.data.Vo.TrailerVo
import com.example.soe_than.movietalkies.ui.ViewModel.DetailViewModel
import com.example.soe_than.movietalkies.ui.ViewModel.SearchDetailViewModel
import com.example.soe_than.movietalkies.ui.ViewModelFactory.DetailViewModelFactory
import com.example.soe_than.movietalkies.ui.ViewModelFactory.SearchDetailViewModelFactory
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_search_detail.*
import kotlinx.android.synthetic.main.detail_movies_content.*
import kotlinx.android.synthetic.main.trailers.*

class SearchDetailActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        var url = v!!.getTag().toString()
        val playVideoIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url.toString()))
        startActivity(playVideoIntent)
    }

    private lateinit var viewModel: SearchDetailViewModel
    private lateinit var viewModelFactory: SearchDetailViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_detail)
        val movieId = intent.getIntExtra("Search", 0)

        Log.i("SearchDetail", "$movieId")

        viewModelFactory = InjectorUtils.provideSearchDetailViewFactory(this, movieId)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SearchDetailViewModel::class.java)

        viewModel.getMovieDetails().observe(this, Observer { movieDetails ->
            if (movieDetails != null) {
                bindMovieDetailsMovie(movieDetails)


            }

        })

        viewModel.getTrailers()?.observe(this, Observer { trailerList ->
            if (trailerList!!.isEmpty()) {
                trailer_label.visibility = View.GONE
                trailer_scroll.visibility = View.GONE
                trailer_container.visibility = View.GONE
            } else {
                bindTrailers(trailerList!!)
            }
        })

    }

    fun bindMovieDetailsMovie(searchDetailVo: MovieDetailVo) {


        Picasso.with(this).load("${Constants.BACKDROP_BASE_URL + searchDetailVo.backdrop_path}").into(search_image)
        Picasso.with(this).load("${Constants.IMAGES_BASE_URL + searchDetailVo.poster_path}").transform(RoundedCornersTransformation(18, 4)).into(poster_image);
        genresChip.setText(Utility.setGenresTypeForMovie1(searchDetailVo.genreids))

        movie_title.setText(searchDetailVo.title)
        movie_overview.setText(searchDetailVo.overview)
        movie_release.setText(searchDetailVo.release_date)
        movie_rat.setText("${searchDetailVo.vote_average}")
        movie_lang.setText(searchDetailVo.original_language.toUpperCase())
    }

    fun bindTrailers(trailerList: List<TrailerVo>) {
        this.trailer_container.removeAllViews()

        val layoutInflater = this.layoutInflater

        for (trailer in trailerList) {
            val thumbContainer = layoutInflater.inflate(R.layout.trailers_content, this.trailer_container, false)
            val thubview = thumbContainer.findViewById(R.id.video_thumb) as ImageView
//
            thubview.setOnClickListener(this);
            thubview.setTag(Utility.getUrl(trailer))




            if (trailer.type.equals("Trailer", true) || trailer.type.equals("Teaser", true)) {
                Picasso.with(this).load(Utility.getThumbnailUrl(trailer)).into(thubview)
                this.trailer_container.addView(thumbContainer)
            }


        }
    }

}

package com.example.soe_than.movietalkies.ui.detail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.example.soe_than.movietalkies.R
import com.example.soe_than.movietalkies.Utils.Constants
import com.example.soe_than.movietalkies.Utils.InjectorUtils
import com.example.soe_than.movietalkies.Utils.Utility
import com.example.soe_than.movietalkies.data.Vo.*
import com.example.soe_than.movietalkies.ui.ViewModel.DetailViewModel
import com.example.soe_than.movietalkies.ui.ViewModel.NowShowingViewModel
import com.example.soe_than.movietalkies.ui.ViewModel.PopularViewModel
import com.example.soe_than.movietalkies.ui.ViewModelFactory.DetailViewModelFactory
import com.example.soe_than.movietalkies.ui.ViewModelFactory.NowShowingViewFactory
import com.example.soe_than.movietalkies.ui.ViewModelFactory.PopularViewFactory
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.detail_movies_content.*
import kotlinx.android.synthetic.main.trailers.*
import android.content.Intent
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import jp.wasabeef.glide.transformations.RoundedCornersTransformation


class DetailActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        var url = v!!.getTag(R.id.glide_tag)
        val playVideoIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url.toString()))
        startActivity(playVideoIntent)
    }

    private lateinit var viewModel: DetailViewModel
    private lateinit var viewModelFactory: DetailViewModelFactory
    private val disposable = CompositeDisposable()

    var nowShowingVo: NowShowingVo? = null
    var popularVo: PopularVo? = null
    var topRatedVo: TopRatedVo? = null
    var upComingVo: UpComingVo? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        var movieId = intent.getIntExtra("ID", 0)
        var movieType = intent.getStringExtra("TYPE")




        viewModelFactory = InjectorUtils.provideDetailViewFactory(this, movieId)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DetailViewModel::class.java)


        when (movieType) {
            "nowshowing" -> {
                viewModel.getNowShowingMovieDetails().observe(this, Observer { nowShowingDetails ->
                    if (nowShowingDetails != null) {
                        nowShowingVo = nowShowingDetails
                        bindNowShowingMovie(nowShowingDetails)


                    }

                })
            }
            "popular" -> {
                viewModel.getPopularMovieDetails().observe(this, Observer { popularDetails ->
                    if (popularDetails != null) {
                        bindPopularMovie(popularDetails)
                        popularVo = popularDetails
                    }
                })
            }
            "toprated" -> {
                viewModel.getTopRatedMovieDetails().observe(this, Observer { topRatedDetails ->
                    if (topRatedDetails != null) {
                        bindTopRatedMovie(topRatedDetails)
                        topRatedVo = topRatedDetails


                    }
                })
            }

            "upcoming" -> {
                viewModel.getUpComingMOvieDetails().observe(this, Observer { upcomingDetails ->
                    if (upcomingDetails != null) {
                        bindUpComingMovie(upcomingDetails)
                        upComingVo = upcomingDetails
                    }
                })
            }

        }
        viewModel.getTrailers()?.observe(this, Observer { trailerList ->
            if (trailerList!!.isEmpty()) {
                trailer_label.visibility = View.GONE
                trailer_scroll.visibility = View.GONE
                trailer_container.visibility = View.GONE
            } else {
                bindTrailers(trailerList!!)
            }
        })

        add_favourite.setOnCheckedChangeListener { buttonView, isChecked ->
            Log.i("Hi", "Clicked")

            when (movieType) {
                "nowshowing" -> {
                    disposable.add(viewModel.addFavouriteMovie(nowShowingVo!!)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe({ add_favourite.isChecked = true },
                                    { error -> Log.e("Hello", "Unable to add movie", error) }))

                }
                "popular" -> {
                    disposable.add(viewModel.addFavouriteMovie(popularVo!!)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe({ add_favourite.isChecked = true },
                                    { error -> Log.e("Hello", "Unable to add movie", error) }))
                }
                "toprated" -> {
                    disposable.add(viewModel.addFavouriteMovie(topRatedVo!!)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe({ add_favourite.isChecked = true }, { error ->
                                Log.i("Hello", "Unable to add movie", error)
                            }))
                }
                "upcoming" -> {
                    disposable.add(viewModel.addFavouriteMovie(upComingVo!!)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe({ add_favourite.isChecked = true }, { error ->
                                Log.i("Hello", "Unable to add movie", error)
                            }))
                }


            }
        }
    }


    fun bindNowShowingMovie(nowShowingVo: NowShowingVo) {

        Glide.with(this).load("${Constants.BACKDROP_BASE_URL + nowShowingVo.backdrop_path}").into(image)
        Glide.with(this).load("${Constants.IMAGES_BASE_URL + nowShowingVo.posterPath}")
                .apply(bitmapTransform(RoundedCornersTransformation(18, 4)))
                .into(poster_image)
        movie_title.setText(nowShowingVo.title)
        movie_overview.setText(nowShowingVo.overview)
        movie_release.setText(nowShowingVo.releasedDate)

    }

    fun bindPopularMovie(popularVo: PopularVo) {

        Glide.with(this).load("${Constants.BACKDROP_BASE_URL + popularVo.backdrop_path}").into(image)
        Glide.with(this).load("${Constants.IMAGES_BASE_URL + popularVo.posterPath}")
                .apply(bitmapTransform(RoundedCornersTransformation(18, 4)))
                .into(poster_image)
        movie_title.setText(popularVo.title)
        movie_overview.setText(popularVo.overview)
        movie_release.setText(popularVo.releasedDate)

    }

    fun bindUpComingMovie(upComingVo: UpComingVo) {

        Glide.with(this).load("${Constants.BACKDROP_BASE_URL + upComingVo.backdrop_path}").into(image)
        Glide.with(this).load("${Constants.IMAGES_BASE_URL + upComingVo.posterPath}")
                .apply(bitmapTransform(RoundedCornersTransformation(18, 4)))
                .into(poster_image)
        movie_title.setText(upComingVo.title)
        movie_overview.setText(upComingVo.overview)
        movie_release.setText(upComingVo.releasedDate)

    }

    fun bindTopRatedMovie(topRatedVo: TopRatedVo) {

        Glide.with(this).load("${Constants.BACKDROP_BASE_URL + topRatedVo.backdrop_path}").into(image)
        Glide.with(this).load("${Constants.IMAGES_BASE_URL + topRatedVo.posterPath}")
                .apply(bitmapTransform(RoundedCornersTransformation(18, 4)))
                .into(poster_image)
        movie_title.setText(topRatedVo.title)
        movie_overview.setText(topRatedVo.overview)
        movie_release.setText(topRatedVo.releasedDate)

    }

    fun bindTrailers(trailerList: List<TrailerVo>) {
        this.trailer_container.removeAllViews()

        val layoutInflater = this.layoutInflater
        val options = RequestOptions()
                .placeholder(R.color.colorPrimary)
                .centerCrop()
                .override(150, 150)

        for (trailer in trailerList) {
            val thumbContainer = layoutInflater.inflate(R.layout.trailers_content, this.trailer_container, false)
            val thubview = thumbContainer.findViewById(R.id.video_thumb) as ImageView
            thubview.clipToOutline = true

            thubview.requestLayout();
            thubview.setOnClickListener(this);



            if (trailer.type.equals("Trailer", true) || trailer.type.equals("Teaser", true)) {
//                thubview.setTag(R.id.glide_tag, Utility.getUrl(trailer))

                Glide.with(this)
                        .load(Utility.getThumbnailUrl(trailer))
                        .apply(options)
                        .into(thubview)



                this.trailer_container.addView(thumbContainer)
            }


        }
    }


    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }


}

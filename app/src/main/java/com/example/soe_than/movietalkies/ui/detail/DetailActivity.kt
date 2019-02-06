package com.example.soe_than.movietalkies.ui.detail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.soe_than.movietalkies.R
import com.example.soe_than.movietalkies.Utils.Constants
import com.example.soe_than.movietalkies.Utils.InjectorUtils
import com.example.soe_than.movietalkies.Utils.Utility
import com.example.soe_than.movietalkies.data.Vo.*
import com.example.soe_than.movietalkies.ui.ViewModel.DetailViewModel
import com.example.soe_than.movietalkies.ui.ViewModelFactory.DetailViewModelFactory
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.detail_movies_content.*
import kotlinx.android.synthetic.main.trailers.*
import android.content.Intent
import android.net.Uri
import android.view.Menu
import android.view.MenuItem
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.ImageView
import com.example.soe_than.movietalkies.ui.search.SearchActivity
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import kotlinx.android.synthetic.main.activity_search_detail.*


class DetailActivity : AppCompatActivity(), View.OnClickListener, CompoundButton.OnCheckedChangeListener {


    override fun onClick(v: View?) {
        var url = v!!.tag.toString()
        val playVideoIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url.toString()))
        startActivity(playVideoIntent)
    }

    private lateinit var viewModel: DetailViewModel
    private lateinit var viewModelFactory: DetailViewModelFactory
    private val disposable = CompositeDisposable()

    private var nowShowingVo: NowShowingVo? = null
    private var popularVo: PopularVo? = null
    private var topRatedVo: TopRatedVo? = null
    private var upComingVo: UpComingVo? = null
    private var favouriteVo: FavouriteVo? = null
    var movieId: Int? = null
    var movieType: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)



        movieId = intent.getIntExtra("ID", 0)
        movieType = intent.getStringExtra("TYPE")
        add_favourite.setOnCheckedChangeListener(this)




        viewModelFactory = InjectorUtils.provideDetailViewFactory(this, movieId!!)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DetailViewModel::class.java)


        when (movieType) {
            "nowshowing" -> {
                viewModel.getNowShowingMovieDetails().observe(this, Observer { nowShowingDetails ->
                    nowShowingDetails!!.let {
                                                nowShowingVo = nowShowingDetails
                        bindNowShowingMovie(nowShowingVo = nowShowingDetails)
                        checkFavouriteStatus()
                    }


                })


            }
            "popular" -> {
                viewModel.getPopularMovieDetails().observe(this, Observer { popularDetails ->
                    if (popularDetails != null) {
                        bindPopularMovie(popularVo = popularDetails)
                        popularVo = popularDetails
                        checkFavouriteStatus()

                    }
                })
            }
            "toprated" -> {
                viewModel.getTopRatedMovieDetails().observe(this, Observer { topRatedDetails ->
                    if (topRatedDetails != null) {
                        bindTopRatedMovie(topRatedVo = topRatedDetails)
                        topRatedVo = topRatedDetails
                        checkFavouriteStatus()


                    }
                })
            }

            "upcoming" -> {
                viewModel.getUpComingMovieDetails().observe(this, Observer { upcomingDetails ->
                    if (upcomingDetails != null) {
                        bindUpComingMovie(upComingVo = upcomingDetails)
                        upComingVo = upcomingDetails
                        checkFavouriteStatus()

                    }
                })

            }


            "favourite" -> {
                viewModel.getFavouriteMovieDetails().observe(this, Observer { favouriteMovieDetails ->
                    if (favouriteMovieDetails != null) {
                        bindFavouriteMovie(favouriteVo = favouriteMovieDetails)
                        favouriteVo = favouriteMovieDetails
                        add_favourite!!.isChecked = true
                    }
                })


            }

        }

        viewModel.getTrailers()?.observe(this, Observer { trailerList ->
            Log.i("DetailActivity","Hii")
            if (trailerList!!.isEmpty()) {
                trailer_label.visibility = View.GONE
                trailer_scroll.visibility = View.GONE
                trailer_container.visibility = View.GONE
            } else {
                bindTrailers(trailerList)
            }
        })

        btn_exit_preview.setOnClickListener {
            finish()
        }

    }


    private fun bindNowShowingMovie(nowShowingVo: NowShowingVo) {

        with(nowShowingVo) {
            Picasso.with(this@DetailActivity).load("${Constants.BACKDROP_BASE_URL + this.backdrop_path}").into(image)
            Picasso.with(this@DetailActivity).load("${Constants.IMAGES_BASE_URL + this.posterPath}").transform(RoundedCornersTransformation(18, 4)).into(poster_image)

            movie_title.text = this.title
            movie_overview.text = this.overview
            movie_release.text = this.releasedDate
            movie_rat.text = this.voteAverage.toString()
            movie_lang.text = this.originalLang

             genresChip.setText(Utility.setGenresTypeForMovie(this.genreids))


        }
    }


    fun bindPopularMovie(popularVo: PopularVo) {

        with(popularVo) {
            Picasso.with(this@DetailActivity).load("${Constants.BACKDROP_BASE_URL + this.backdrop_path}").into(image)
            Picasso.with(this@DetailActivity).load("${Constants.IMAGES_BASE_URL + this.posterPath}").transform(RoundedCornersTransformation(18, 4)).into(poster_image)

            movie_title.text = this.title
            movie_overview.text = this.overview
            movie_release.text = this.releasedDate
            movie_rat.text = this.voteAverage.toString()
            movie_lang.text = this.originalLang

            genresChip.setText(Utility.setGenresTypeForMovie(this.genreids))


        }

    }

    fun bindUpComingMovie(upComingVo: UpComingVo) {

        with(upComingVo) {
            Picasso.with(this@DetailActivity).load("${Constants.BACKDROP_BASE_URL + this.backdrop_path}").into(image)
            Picasso.with(this@DetailActivity).load("${Constants.IMAGES_BASE_URL + this.posterPath}").transform(RoundedCornersTransformation(18, 4)).into(poster_image)

            movie_title.text = this.title
            movie_overview.text = this.overview
            movie_release.text = this.releasedDate
            movie_rat.text = this.voteAverage.toString()
            movie_lang.text = this.originalLang

            genresChip.setText(Utility.setGenresTypeForMovie(this.genreids))

        }

    }

    fun bindTopRatedMovie(topRatedVo: TopRatedVo) {
        with(topRatedVo) {
            Picasso.with(this@DetailActivity).load("${Constants.BACKDROP_BASE_URL + this.backdrop_path}").into(image)
            Picasso.with(this@DetailActivity).load("${Constants.IMAGES_BASE_URL + this.posterPath}").transform(RoundedCornersTransformation(18, 4)).into(poster_image)

            movie_title.text = this.title
            movie_overview.text = this.overview
            movie_release.text = this.releasedDate
            movie_rat.text = this.voteAverage.toString()
            movie_lang.text = this.originalLang

            genresChip.setText(Utility.setGenresTypeForMovie(this.genreids))


        }

    }

    fun bindFavouriteMovie(favouriteVo: FavouriteVo) {


        with(favouriteVo) {
            Picasso.with(this@DetailActivity).load("${Constants.BACKDROP_BASE_URL + this.backdrop_path}").into(image)
            Picasso.with(this@DetailActivity).load("${Constants.IMAGES_BASE_URL + this.posterPath}").transform(RoundedCornersTransformation(18, 4)).into(poster_image)

            movie_title.text = this.title
            movie_overview.text = this.overview
            movie_release.text = this.releasedDate
            movie_rat.text = this.voteAverage.toString()
            movie_lang.text = this.originalLang

            genresChip.setText(Utility.setGenresTypeForMovie(this.genreids))


        }


    }

    fun bindTrailers(trailerList: List<TrailerVo>) {
        this.trailer_container.removeAllViews()

        val layoutInflater = this.layoutInflater

        for (trailer in trailerList) {
            val thumbContainer = layoutInflater.inflate(R.layout.trailers_content, this.trailer_container, false)
            val thubview = thumbContainer.findViewById(R.id.video_thumb) as ImageView

            thubview.setOnClickListener(this)
            thubview.tag = Utility.getUrl(trailer)




            if (trailer.type.equals("Trailer", true) || trailer.type.equals("Teaser", true)) {
                Picasso.with(this).load(Utility.getThumbnailUrl(trailer)).into(thubview)
                this.trailer_container.addView(thumbContainer)
            }


        }
    }

    fun checkFavouriteStatus() {
        disposable.add(viewModel.checkedFavourite()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ movieCount ->
                    Log.i("checkFavoutite", "$movieCount")
                    add_favourite.isChecked = movieCount != 0

                },
                        { throwable -> Log.e("DetailViewModel", "Unable to count", throwable) }))

    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        when (movieType) {
            "nowshowing" -> {
                disposable.add(viewModel.favouriteStatus(nowShowingVo!!, isChecked)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ add_favourite.isChecked = isChecked },
                                { error -> Log.e("Hello", "Unable to Perform", error) }))
            }
            "upcoming" -> {
                disposable.add(viewModel.favouriteStatus(upComingVo!!, isChecked)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ add_favourite.isChecked = isChecked }, { error ->
                            Log.i("Hello", "Unable to Perform", error)
                        }))

            }
            "popular" -> {
                disposable.add(viewModel.favouriteStatus(popularVo!!, isChecked)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ add_favourite.isChecked = isChecked },
                                { error -> Log.e("Hello", "Unable to Perform", error) }))
            }
            "toprated" -> {
                disposable.add(viewModel.favouriteStatus(topRatedVo!!, isChecked)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ add_favourite.isChecked = isChecked }, { error ->
                            Log.i("Hello", "Unable to Perform", error)
                        }))
            }
            "favourite" -> {
                disposable.add(viewModel.favouriteStatus(favouriteVo!!, isChecked)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            add_favourite.isChecked = isChecked
                            Log.i("Hello", "Removed")
                        }, { error ->
                            Log.i("Hello", "Unable to remove movie", error)
                        }))
            }


        }


    }


    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }

    override fun onStart() {
        super.onStart()
    }




}

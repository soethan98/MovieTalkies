package com.soethan.movietalkies.ui.detail

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CompoundButton
import android.widget.ImageView
import com.soethan.movietalkies.R
import com.soethan.movietalkies.Utils.*
import com.soethan.movietalkies.data.Vo.MovieDetailVo
import com.soethan.movietalkies.data.Vo.TrailerVo
import com.soethan.movietalkies.ui.ViewModel.SearchDetailViewModel
import com.soethan.movietalkies.ui.ViewModelFactory.MainViewModelFactory
import com.squareup.picasso.Picasso
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import kotlinx.android.synthetic.main.activity_search_detail.*
import kotlinx.android.synthetic.main.detail_movies_content.*
import kotlinx.android.synthetic.main.trailers.*
import javax.inject.Inject

class SearchDetailActivity : AppCompatActivity(), View.OnClickListener, CompoundButton.OnCheckedChangeListener, HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>


    override fun onClick(v: View?) {
        var url = v!!.tag.toString()
        val playVideoIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url.toString()))
        startActivity(playVideoIntent)
    }

    private lateinit var viewModel: SearchDetailViewModel

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory
    var movieDetailVo: MovieDetailVo? = null


    private val disposable = CompositeDisposable()
    var movieId: Int? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_detail)
        movieId = intent.getIntExtra("Search", 0)


        add_search_favourite.setOnCheckedChangeListener(this)


        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SearchDetailViewModel::class.java)

        viewModel.getMovieDetails(movieId!!).observe(this, Observer { movieDetails ->
            movieDetails!!.let {
                bindMovieDetailsMovie(movieDetails)
                movieDetailVo = movieDetails
                checkFavouriteStatus(movieId ?: 0)

            }

        })

        viewModel.getTrailers(movieId!!)?.observe(this, Observer { trailerList ->
            if (trailerList!!.isEmpty()) {
                trailer_label.visibility = View.GONE
                trailer_scroll.visibility = View.GONE
                trailer_container.visibility = View.GONE
            } else {
                bindTrailers(trailerList)
            }
        })

        btn_exit_search.setOnClickListener {
            finish()
        }

    }

    fun bindMovieDetailsMovie(searchDetailVo: MovieDetailVo) {


        Picasso.with(this).load(BACKDROP_BASE_URL + searchDetailVo.backdrop_path).into(search_image)
        Picasso.with(this).load(IMAGES_BASE_URL + searchDetailVo.poster_path).transform(RoundedCornersTransformation(18, 4)).into(poster_image)
        genresChip.setText(Utility
                .setGenresTypeForMovie1(searchDetailVo.genreids))

        movie_title.text = searchDetailVo.title
        movie_overview.text = searchDetailVo.overview
        movie_release.text = searchDetailVo.release_date
        movie_rat.text = "${searchDetailVo.vote_average}"
        movie_lang.text = searchDetailVo.original_language.toUpperCase()

    }

    private fun bindTrailers(trailerList: List<TrailerVo>) {
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

    private fun checkFavouriteStatus(movieId: Int) {
        disposable.add(viewModel.checkedFavourite(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ movieCount ->
                    Log.i("SearchDetail", "$movieCount checkFavourite")
                    add_search_favourite.isChecked = movieCount != 0

                },
                        { throwable -> Log.e("SearchDetailViewModel", "Unable to count", throwable) }))

    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {


        disposable.add(viewModel.favouriteStatus(movieDetailVo!!, isChecked)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ add_search_favourite.isChecked = isChecked },
                        { error -> Log.e("SearchDetailViewModel", "Unable to Perform", error) }))
    }


    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }


}

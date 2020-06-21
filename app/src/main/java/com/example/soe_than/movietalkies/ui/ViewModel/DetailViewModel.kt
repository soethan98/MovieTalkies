package com.example.soe_than.movietalkies.ui.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import com.example.soe_than.movietalkies.data.Vo.*
import com.example.soe_than.movietalkies.data.repository.MoviesRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val moviesRepository: MoviesRepository) : ViewModel() {


    private val _popularMovieDatailResultLiveData = MutableLiveData<PopularVo>()
    val popularMovieDetailResultLiveData: LiveData<PopularVo>
        get() = _popularMovieDatailResultLiveData

    private val _topRatedMovieDatailResultLiveData = MutableLiveData<TopRatedVo>()
    val topRatedMovieDetailResultLiveData: LiveData<TopRatedVo>
        get() = _topRatedMovieDatailResultLiveData

    private val _nowShowingMovieDatailResultLiveData = MutableLiveData<NowShowingVo>()
    val nowShowingMovieDetailResultLiveData: LiveData<NowShowingVo>
        get() = _nowShowingMovieDatailResultLiveData

    private val _upcomingMovieDatailResultLiveData = MutableLiveData<UpComingVo>()
    val upcomingMovieDetailResultLiveData: LiveData<UpComingVo>
        get() = _upcomingMovieDatailResultLiveData


    private val _favouriteMovieDatailResultLiveData = MutableLiveData<FavouriteVo>()
    val favouriteMovieDetailResultLiveData: LiveData<FavouriteVo>
        get() = _favouriteMovieDatailResultLiveData


    private val compositeDisposable = CompositeDisposable()

    fun getPopularMovieDetails(id: Int) {
        compositeDisposable.add(moviesRepository.getPopularMovieDetails(id).subscribeOn(Schedulers.io())
                .subscribe({
                    _popularMovieDatailResultLiveData.postValue(it
                    )
                }, { t: Throwable ->
                    Log.i("error", t.message)

                }))
    }

    fun getNowShowingMovieDetails(id: Int) {
        compositeDisposable.add(moviesRepository.getNowShowingMovieDetail(id).subscribeOn(Schedulers.io())
                .subscribe({
                    _nowShowingMovieDatailResultLiveData.postValue(it
                    )
                }, { t: Throwable ->
                    Log.i("error", t.message)

                }))
    }

    fun getTopRatedMovieDetails(id: Int) {
        compositeDisposable.add(moviesRepository.getTopRatedMovieDetails(id).subscribeOn(Schedulers.io())
                .subscribe({
                    _topRatedMovieDatailResultLiveData.postValue(it
                    )
                }, { t: Throwable ->
                    Log.i("error", t.message)

                }))
    }

    fun getUpComingMovieDetails(id: Int) {
        compositeDisposable.add(moviesRepository.getUpComingMovieDetails(id).subscribeOn(Schedulers.io())
                .subscribe({
                    _upcomingMovieDatailResultLiveData.postValue(it
                    )
                }, { t: Throwable ->
                    Log.i("error", t.message)

                }))
    }


    fun getTrailers(id: Int): LiveData<List<TrailerVo>>? {
        return moviesRepository.getTrailers(id)
    }

    fun getFavouriteMovieDetails(id: Int) {
        compositeDisposable.add(moviesRepository.getFavouriteMovieDetails(id).subscribeOn(Schedulers.io())
                .subscribe({
                    _favouriteMovieDatailResultLiveData.postValue(it
                    )
                }, { t: Throwable ->
                    Log.i("error", t.message)

                }))
    }


    fun checkedFavourite(id: Int): Single<Int> = moviesRepository.checkedFavouriteMovie(id!!)


    fun favouriteStatus(nowShowingVo: NowShowingVo, checked: Boolean): Completable {

        Log.i("DetailViewModel", "${nowShowingVo.genreids.get(1)}")
        val favourite = FavouriteVo(nowShowingVo.id, nowShowingVo.posterPath, nowShowingVo.overview, nowShowingVo.title, nowShowingVo.releasedDate, nowShowingVo.voteAverage, nowShowingVo.backdrop_path, nowShowingVo.genreids, nowShowingVo.originalLang)


        return if (checked) {
            Completable.fromAction {
                moviesRepository.addFavouriteMovie(favourite)
            }
        } else {
            Completable.fromAction {

                moviesRepository.removeFavouriteMovie(favourite)
            }
        }


    }

    fun favouriteStatus(upComingVo: UpComingVo, checked: Boolean): Completable {
        val favourite = FavouriteVo(upComingVo.id, upComingVo.posterPath, upComingVo.overview, upComingVo.title, upComingVo.releasedDate, upComingVo.voteAverage, upComingVo.backdrop_path, upComingVo.genreids, upComingVo.originalLang)
        return if (checked) {
            Completable.fromAction {
                moviesRepository.addFavouriteMovie(favourite)
            }
        } else {
            Completable.fromAction {

                moviesRepository.removeFavouriteMovie(favourite)
            }
        }
    }

    fun favouriteStatus(topRatedVo: TopRatedVo, checked: Boolean): Completable {
        val favourite = FavouriteVo(topRatedVo.id, topRatedVo.posterPath, topRatedVo.overview, topRatedVo.title, topRatedVo.releasedDate, topRatedVo.voteAverage, topRatedVo.backdrop_path, topRatedVo.genreids, topRatedVo.originalLang)
        return if (checked) {
            Completable.fromAction {
                moviesRepository.addFavouriteMovie(favourite)
            }
        } else {
            Completable.fromAction {

                moviesRepository.removeFavouriteMovie(favourite)
            }
        }
    }

    fun favouriteStatus(popularVo: PopularVo, checked: Boolean): Completable {
        val favourite = FavouriteVo(popularVo.id, popularVo.posterPath, popularVo.overview, popularVo.title, popularVo.releasedDate, popularVo.voteAverage, popularVo.backdrop_path, popularVo.genreids, popularVo.originalLang)
        return if (checked) {
            Completable.fromAction {
                moviesRepository.addFavouriteMovie(favourite)
            }
        } else {
            Completable.fromAction {

                moviesRepository.removeFavouriteMovie(favourite)
            }
        }
    }

    fun favouriteStatus(favouriteVo: FavouriteVo, checked: Boolean): Completable {

        return if (checked) {
            Completable.fromAction {
                moviesRepository.addFavouriteMovie(favouriteVo)
            }
        } else {
            Completable.fromAction {

                moviesRepository.removeFavouriteMovie(favouriteVo)
            }
        }
    }


}
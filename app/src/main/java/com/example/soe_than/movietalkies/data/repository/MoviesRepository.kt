package com.example.soe_than.movietalkies.data.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.util.Log
import com.example.soe_than.movietalkies.Utils.APIKEY
import com.example.soe_than.movietalkies.Utils.APIKEY.API_KEY
import com.example.soe_than.movietalkies.Utils.LOG_TAG
import com.example.soe_than.movietalkies.Utils.Utility
import com.example.soe_than.movietalkies.api.ApiService
import com.example.soe_than.movietalkies.data.Vo.*
import com.example.soe_than.movietalkies.data.local.Daos.MovieDao
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MoviesRepository(val movieDao: MovieDao, val context: Context) {

    var apiService: ApiService
    var trailerData: MutableLiveData<List<TrailerVo>>
    var favouriteData: MutableLiveData<List<FavouriteVo>>
    var searchData: MutableLiveData<List<SearchVo>>
    var movieDetailData: MutableLiveData<MovieDetailVo>

    init {

        apiService = ApiService.create()
        trailerData = MutableLiveData()
        favouriteData = MutableLiveData()
        searchData = MutableLiveData()
        movieDetailData = MutableLiveData()
    }

    companion object {
        var sInstance: MoviesRepository? = null
        val LOCK = Object()

        fun getInstance(movieDao: MovieDao, context: Context): MoviesRepository? {
            Log.d(LOG_TAG, "Getting the repository")
            if (sInstance == null) {
                synchronized(LOCK) {

                    sInstance = MoviesRepository(movieDao, context)
                    Log.d(LOG_TAG, "Made new repository")

                }
            }
            return sInstance
        }


    }

    fun getNowShowingMovies(): LiveData<List<NowShowingVo>> {
        apiService.getNowShowingMovies(API_KEY)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .map { nowShowingResponse -> nowShowingResponse.nowShowingVo }
                .subscribe({ nowShowingList: List<NowShowingVo> -> movieDao.saveAllNowShowingMovies(nowShowingList) },
                        { t: Throwable -> Log.i("error: %s", t.message) })

        return movieDao.getAllNowShowingMovies()
    }

    fun getPopularMovies(): LiveData<List<PopularVo>> {
        apiService.getPopularMovies(API_KEY)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .map { popularResponse -> popularResponse.popularVo }
                .subscribe({ popularList: List<PopularVo> -> movieDao.saveAllPopularMovies(popularList) },
                        { t: Throwable -> Log.i("error: %s", t.message) })

        return movieDao.getAllPopularMovies()
    }

    fun getTopRatedMovies(): LiveData<List<TopRatedVo>> {
        apiService.getTopRatedMovies(API_KEY)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .map { topRatedResponse -> topRatedResponse.topRatedVo }
                .subscribe({ topRatedList: List<TopRatedVo> -> movieDao.saveAllTopRatedMovies(topRatedList) },
                        { t: Throwable -> Log.i("error: %s", t.message) })

        return movieDao.getAllTopRatedMovies()
    }

    fun getUpComingMovies(): LiveData<List<UpComingVo>> {
        apiService.getUpComingMovies(API_KEY)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .map { upComingResponse -> upComingResponse.upComingVo }
                .subscribe({ upComingList: List<UpComingVo> ->
                   movieDao.saveAllUpComingMovies(upComingList)
                },
                        { t: Throwable -> Log.i("error: %s", t.message) })

        return movieDao.getAllUpComingMovies()
    }

    fun getTrailers(id: Int): LiveData<List<TrailerVo>>? {
        return if (Utility.isNetworkAvailable(context)) {
            apiService.getTrailers(id, API_KEY)
                    .subscribeOn(Schedulers.io()).toObservable().map { trailerResponse ->
                        trailerResponse.results
                    }
                    .subscribe({ trailerList: List<TrailerVo> -> trailerData.postValue(trailerList) }, { t: Throwable -> Log.i("error: %s", t.message) })
             trailerData
        } else {
            null
        }

    }

    fun getSearchList(query: String): LiveData<List<SearchVo>> {
        apiService.getSearchResult(API_KEY, query)
                .subscribeOn(Schedulers.io()).toObservable().map { searchResponse ->
                    searchResponse.searchResult
                }.subscribe({ searchList: List<SearchVo> ->

                    searchData.postValue(searchList)
                }, { t: Throwable ->
                    Log.i("error: %s", t.message)
                })
        return searchData
    }


    fun getFavourites(): LiveData<List<FavouriteVo>> {
        movieDao.getAllFavouriteMovies().subscribeOn(Schedulers.io()).toObservable().map { t: List<FavouriteVo> -> t }
                .subscribe({ favouriteList -> favouriteData.postValue(favouriteList) }, { t: Throwable -> Log.i("error: %s", t.message) })

        return favouriteData
    }



    fun getSearchMovieDetails(id: Int): LiveData<MovieDetailVo> {
        apiService.getMovieDetail(id,API_KEY)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .subscribe({ movieDetail: MovieDetailVo ->
                    movieDetailData.postValue(movieDetail)
                }, { t: Throwable ->
                    Log.i("error: %s", t.message)
                })
        return movieDetailData
    }

    fun addFavouriteMovie(favouriteVo: FavouriteVo) {
        movieDao.saveFavouriteMovies(favouriteVo)
    }

    fun removeFavouriteMovie(favouriteVo: FavouriteVo) {
        movieDao.clearFavoutireMoive(favouriteVo)
    }

    fun getPopularMovieDetails(id: Int): LiveData<PopularVo> {
        return movieDao.getPopularMovieById(id.toString())
    }

    fun getTopRatedMovieDetails(id: Int): LiveData<TopRatedVo> {
        return movieDao.getTopRatedMovieByID(id.toString())
    }

    fun getNowShowingMovieDetail(id: Int): LiveData<NowShowingVo> {
        return movieDao.getNowShowingMovieById(id.toString())
    }

    fun getUpComingMovieDetails(id: Int): LiveData<UpComingVo> {
        return movieDao.getUpComingMovieById(id.toString())
    }

    fun getFavouriteMovieDetails(id: Int): LiveData<FavouriteVo> {
        return movieDao.getFavouriteMovieById(id.toString())
    }

    fun checkedFavouriteMovie(id:Int):Single<Int> {

       return movieDao.isFavouriteMovie(id.toString()!!)
    }











}
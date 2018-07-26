package com.example.soe_than.movietalkies.data.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.util.Log
import com.example.soe_than.movietalkies.Utils.Constants
import com.example.soe_than.movietalkies.Utils.Utility
import com.example.soe_than.movietalkies.api.ApiService
import com.example.soe_than.movietalkies.data.Vo.*
import com.example.soe_than.movietalkies.data.local.Daos.MovieDao
import io.reactivex.schedulers.Schedulers

class MoviesRepository(val movieDao: MovieDao, val context: Context) {

    var apiService: ApiService
    var trailerData: MutableLiveData<List<TrailerVo>>

    init {

        apiService = ApiService.create()
        trailerData = MutableLiveData()
    }

    companion object {
        var sInstance: MoviesRepository? = null
        val LOCK = Object()

        fun getInstance(movieDao: MovieDao, context: Context): MoviesRepository? {
            Log.d(Constants.LOG_TAG, "Getting the repository")
            if (sInstance == null) {
                synchronized(LOCK) {

                    sInstance = MoviesRepository(movieDao, context)
                    Log.d(Constants.LOG_TAG, "Made new repository")

                }
            }
            return sInstance
        }


    }

    fun getNowShowingMovies(): LiveData<List<NowShowingVo>> {
        apiService.getNowShowingMovies(Constants.API_KEY)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .map { nowShowingResponse -> nowShowingResponse.nowShowingVo }
                .subscribe({ nowShowingList: List<NowShowingVo> -> movieDao.saveAllNowShowingMovies(nowShowingList) },
                        { t: Throwable -> Log.i("error: %s", t.message) })

        return movieDao.getAllNowShowingMovies()
    }

    fun getPopularMovies(): LiveData<List<PopularVo>> {
        apiService.getPopularMovies(Constants.API_KEY)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .map { popularResponse -> popularResponse.popularVo }
                .subscribe({ popularList: List<PopularVo> -> movieDao.saveAllPopularMovies(popularList) },
                        { t: Throwable -> Log.i("error: %s", t.message) })

        return movieDao.getAllPopularMovies()
    }

    fun getTopRatedMovies(): LiveData<List<TopRatedVo>> {
        apiService.getTopRatedMovies(Constants.API_KEY)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .map { topRatedResponse -> topRatedResponse.topRatedVo }
                .subscribe({ topRatedList: List<TopRatedVo> -> movieDao.saveAllTopRatedMovies(topRatedList) },
                        { t: Throwable -> Log.i("error: %s", t.message) })

        return movieDao.getAllTopRatedMovies()
    }

    fun getUpComingMovies(): LiveData<List<UpComingVo>> {
        apiService.getUpComingMovies(Constants.API_KEY)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .map { upComingResponse -> upComingResponse.upComingVo }
                .subscribe({ upComingList: List<UpComingVo> -> movieDao.saveAllUpComingMovies(upComingList) },
                        { t: Throwable -> Log.i("error: %s", t.message) })

        return movieDao.getAllUpComingMovies()
    }

    fun getTrailers(id: Int): LiveData<List<TrailerVo>>? {
        if (Utility.isNetworkAvailable(context)) {
            apiService.getTrailers(id, Constants.API_KEY)
                    .subscribeOn(Schedulers.io()).toObservable().map { trailerResponse -> trailerResponse.results }
                    .subscribe({ trailerList: List<TrailerVo> -> trailerData.postValue(trailerList) }, { t: Throwable -> Log.i("error: %s", t.message) })
            return trailerData
        } else {
            return null
        }

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
}
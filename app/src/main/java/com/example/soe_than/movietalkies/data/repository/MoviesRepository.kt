package com.example.soe_than.movietalkies.data.repository

import android.arch.lifecycle.LiveData
import android.graphics.Movie
import android.util.Log
import com.example.soe_than.movietalkies.Utils.Constants
import com.example.soe_than.movietalkies.api.ApiService
import com.example.soe_than.movietalkies.data.Vo.NowShowingVo
import com.example.soe_than.movietalkies.data.Vo.PopularVo
import com.example.soe_than.movietalkies.data.Vo.TopRatedVo
import com.example.soe_than.movietalkies.data.Vo.UpComingVo
import com.example.soe_than.movietalkies.data.local.Daos.MovieDao
import com.example.soe_than.movietalkies.data.local.Daos.NowShowingDao
import io.reactivex.schedulers.Schedulers

class MoviesRepository(val movieDao: MovieDao) {

    var apiService: ApiService

    init {

        apiService = ApiService.create()
    }

    companion object {
        var sInstance: MoviesRepository? = null
        val LOCK = Object()

        fun getInstance(movieDao: MovieDao): MoviesRepository? {
            Log.d(Constants.LOG_TAG, "Getting the repository")
            if (sInstance == null) {
                synchronized(LOCK) {

                    sInstance = MoviesRepository(movieDao)
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
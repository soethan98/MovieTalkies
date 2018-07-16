package com.example.soe_than.movietalkies.data.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.example.soe_than.movietalkies.Utils.Constants
import com.example.soe_than.movietalkies.api.ApiService
import com.example.soe_than.movietalkies.data.Vo.PopularVo
import com.example.soe_than.movietalkies.data.local.Daos.NowShowingDao
import com.example.soe_than.movietalkies.data.local.Daos.PopularDao
import com.example.soe_than.movietalkies.data.network.MovieNetworkDataSource
import io.reactivex.schedulers.Schedulers

class PopularRepository(val popularDao: PopularDao) {


    var apiService: ApiService

    init {

        apiService = ApiService.create()

    }

    companion object {
        var sInstance: PopularRepository? = null
        val LOCK = Object()

        fun getInstance(popularDao: PopularDao): PopularRepository? {
            Log.d(Constants.LOG_TAG, "Getting the repository")
            if (sInstance == null) {
                synchronized(LOCK) {

                    sInstance = PopularRepository(popularDao)
                    Log.d(Constants.LOG_TAG, "Made new repository")

                }
            }
            return sInstance
        }


    }

    fun getPopularMovies(): LiveData<List<PopularVo>> {
        apiService.getPopularMovies(Constants.API_KEY)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .map { popularResponse -> popularResponse.popularVo }
                .subscribe({ popularList: List<PopularVo> -> popularDao.saveAll(popularList) },
                        { t: Throwable -> Log.i("error: %s", t.message) })

        return popularDao.getAllMovies()
    }

}
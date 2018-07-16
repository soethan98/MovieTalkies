package com.example.soe_than.movietalkies.data.repository

import android.arch.lifecycle.LiveData
import android.util.Log
import com.example.soe_than.movietalkies.Utils.Constants
import com.example.soe_than.movietalkies.api.ApiService
import com.example.soe_than.movietalkies.data.Vo.PopularVo
import com.example.soe_than.movietalkies.data.Vo.TopRatedVo
import com.example.soe_than.movietalkies.data.local.Daos.NowShowingDao
import com.example.soe_than.movietalkies.data.local.Daos.TopRatedDao
import com.example.soe_than.movietalkies.data.response.TopRatedResponse
import io.reactivex.schedulers.Schedulers

class TopRatedRepository(val topRatedDao: TopRatedDao) {


    var apiService: ApiService

    init {

        apiService = ApiService.create()

    }

    companion object {
         var sInstance: TopRatedRepository? = null
        val LOCK = Object()

        fun getInstance(topRatedDao: TopRatedDao): TopRatedRepository? {
            Log.d(Constants.LOG_TAG, "Getting the repository")
            if (sInstance == null) {
                synchronized(LOCK) {

                    sInstance = TopRatedRepository(topRatedDao)
                    Log.d(Constants.LOG_TAG, "Made new repository")

                }
            }
            return sInstance
        }


    }

    fun getTopRatedMovies(): LiveData<List<TopRatedVo>> {
        apiService.getTopRatedMovies(Constants.API_KEY)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .map { topRatedResponse -> topRatedResponse.topRatedVo }
                .subscribe({ topRatedList: List<TopRatedVo> -> topRatedDao.saveAll(topRatedList) },
                        { t: Throwable -> Log.i("error: %s", t.message) })

        return topRatedDao.getAllMovies()
    }
}
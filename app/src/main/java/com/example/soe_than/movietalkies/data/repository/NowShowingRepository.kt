package com.example.soe_than.movietalkies.data.repository

import android.arch.lifecycle.LiveData
import android.content.Context
import android.util.Log
import com.example.soe_than.movietalkies.Utils.Constants
import com.example.soe_than.movietalkies.Utils.Constants.LOG_TAG
import com.example.soe_than.movietalkies.api.ApiService
import com.example.soe_than.movietalkies.data.Vo.NowShowingVo
import com.example.soe_than.movietalkies.data.Vo.PopularVo
import com.example.soe_than.movietalkies.data.local.Daos.NowShowingDao
import io.reactivex.schedulers.Schedulers
import com.example.soe_than.movietalkies.data.network.MovieNetworkDataSource


class NowShowingRepository(val nowShowingDao: NowShowingDao) {


    var apiService: ApiService

    init {

        apiService = ApiService.create()

    }

    companion object {
         var sInstance: NowShowingRepository? = null
        val LOCK = Object()

        fun getInstance(nowShowingDao: NowShowingDao): NowShowingRepository?{
            Log.d(LOG_TAG, "Getting the repository")
            if (sInstance == null) {
                synchronized(LOCK) {

                    sInstance = NowShowingRepository(nowShowingDao)
                    Log.d(LOG_TAG, "Made new repository")

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
                .subscribe({ nowShowingList: List<NowShowingVo> -> nowShowingDao.saveAll(nowShowingList) },
                        { t: Throwable -> Log.i("error: %s", t.message) })

        return nowShowingDao.getAllMovies()
    }
}
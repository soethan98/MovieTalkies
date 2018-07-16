package com.example.soe_than.movietalkies.data.repository

import android.arch.lifecycle.LiveData
import android.util.Log
import com.example.soe_than.movietalkies.Utils.Constants
import com.example.soe_than.movietalkies.api.ApiService
import com.example.soe_than.movietalkies.data.Vo.NowShowingVo
import com.example.soe_than.movietalkies.data.Vo.UpComingVo
import com.example.soe_than.movietalkies.data.local.Daos.NowShowingDao
import com.example.soe_than.movietalkies.data.local.Daos.UpComingDao
import io.reactivex.schedulers.Schedulers

class UpComingRepository(val upComingDao: UpComingDao) {

    var apiService: ApiService

    init {

        apiService = ApiService.create()

    }

    companion object {
         var sInstance: UpComingRepository? = null
        val LOCK = Object()

        fun getInstance(upComingDao: UpComingDao): UpComingRepository? {
            Log.d(Constants.LOG_TAG, "Getting the repository")
            if (sInstance == null) {
                synchronized(LOCK) {

                    sInstance = UpComingRepository(upComingDao)
                    Log.d(Constants.LOG_TAG, "Made new repository")

                }
            }
            return sInstance
        }


    }


    fun getUpComingMovies(): LiveData<List<UpComingVo>> {
        apiService.getUpComingMovies(Constants.API_KEY)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .map { upComingResponse -> upComingResponse.upComingVo }
                .subscribe({ upComingList: List<UpComingVo> -> upComingDao.saveAll(upComingList) },
                        { t: Throwable -> Log.i("error: %s", t.message) })

        return upComingDao.getAllMovies()
    }
}
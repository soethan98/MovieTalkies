package com.example.soe_than.movietalkies.data.network

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.soe_than.movietalkies.Utils.Constants
import com.example.soe_than.movietalkies.api.ApiService
import io.reactivex.schedulers.Schedulers
import android.util.Log
import com.example.soe_than.movietalkies.data.Vo.NowShowingVo
import com.example.soe_than.movietalkies.data.Vo.PopularVo
import com.example.soe_than.movietalkies.data.Vo.TopRatedVo
import com.example.soe_than.movietalkies.data.Vo.UpComingVo
import com.example.soe_than.movietalkies.data.response.NowShowingResponse
import com.example.soe_than.movietalkies.data.response.PopularResponse
import com.example.soe_than.movietalkies.data.response.TopRatedResponse
import com.example.soe_than.movietalkies.data.response.UpcomingResponse


class MovieNetworkDataSource {

    var apiService: ApiService
    var popularLiveData: MutableLiveData<List<PopularVo>>
    var upComingLiveData: MutableLiveData<List<UpComingVo>>
    var topRatedLiveData: MutableLiveData<List<TopRatedVo>>
    var nowShowingLiveData: MutableLiveData<List<NowShowingVo>>


    init {

        apiService = ApiService.create()
        popularLiveData = MutableLiveData()
        upComingLiveData = MutableLiveData()
        topRatedLiveData = MutableLiveData()
        nowShowingLiveData = MutableLiveData()

    }

    fun getPopularMovies(): LiveData<List<PopularVo>> {
        apiService.getPopularMovies(Constants.API_KEY)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .map { popularResponse -> popularResponse.popularResponse }
                .subscribe({ popularList: List<PopularVo> -> popularLiveData.postValue(popularList) },
                        { t: Throwable -> Log.i("error: %s", t.message) })

        return popularLiveData
    }

    fun getTopRatedMovies(): LiveData<List<TopRatedVo>> {
        apiService.getTopRatedMovies(Constants.API_KEY)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .map { topRatedResponse -> topRatedResponse.topRatedResponse }
                .subscribe({ topRatedList: List<TopRatedVo> -> topRatedLiveData.postValue(topRatedList) },
                        { t: Throwable -> Log.i("error: %s", t.message) })

        return topRatedLiveData

    }

    fun getNowShowingMovies(): LiveData<List<NowShowingVo>> {
        apiService.getNowShowingMovies(Constants.API_KEY)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .map { nowshowingResponse: NowShowingResponse -> nowshowingResponse.nowShowingResponse }
                .subscribe({ nowShowingList: List<NowShowingVo> -> nowShowingLiveData.postValue(nowShowingList) },
                        { t: Throwable -> Log.i("error: %s", t.message) })

        return nowShowingLiveData
    }

    fun getUpComingMovies(): LiveData<List<UpComingVo>> {

        apiService.getUpComingMovies(Constants.API_KEY)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .map { upcomingResponse: UpcomingResponse -> upcomingResponse.upComingVo }
                .subscribe({ upcomingList: List<UpComingVo> -> upComingLiveData.postValue(upcomingList) },
                        { t: Throwable -> Log.i("error: %s", t.message) })

        return upComingLiveData
    }

}


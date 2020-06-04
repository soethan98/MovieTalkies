package com.example.soe_than.movietalkies.ui.ViewModel

import android.app.DownloadManager
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.example.soe_than.movietalkies.Utils.APIKEY
import com.example.soe_than.movietalkies.data.Vo.*
import com.example.soe_than.movietalkies.data.repository.MoviesRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovieViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()




    fun getNowShowingMovies(): LiveData<List<NowShowingVo>> = moviesRepository.getNowShowingMovies()


    fun getPopularMovies(): LiveData<List<PopularVo>> = moviesRepository.getPopularMovies()


    fun getTopRatedMovies(): LiveData<List<TopRatedVo>> = moviesRepository.getTopRatedMovies()


    fun getUpComingMovies(): LiveData<List<UpComingVo>> = moviesRepository.getUpComingMovies()


    private val _searchResultLiveData = MutableLiveData<List<SearchVo>>()
    val searchResultLiveData: LiveData<List<SearchVo>>
        get() = _searchResultLiveData


    fun getSearchList(query: String) {
        compositeDisposable.add(moviesRepository.getSearchMovie(query)
                .subscribeOn(Schedulers.io()).toObservable().map { searchResponse ->
                    searchResponse.searchResult
                }.subscribe({ searchList: List<SearchVo> ->
                    _searchResultLiveData.postValue(searchList)

                }, { t: Throwable ->
                    Log.i("error: %s", t.message)
                }))
    }


}
package com.soethan.movietalkies.ui.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Log
import com.soethan.movietalkies.data.Vo.*
import com.soethan.movietalkies.data.repository.MoviesRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class MovieViewModel @Inject constructor(var moviesRepository: MoviesRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()




    fun getNowShowingMovies(): LiveData<List<NowShowingVo>> {
        Log.i("ViewModel",moviesRepository.getNowShowingMovies().value?.size.toString()+"---")

        return moviesRepository.getNowShowingMovies()
    }


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
                    Log.i("error: %s", t.message ?: "Something went wrong")
                }))
    }


}
package com.example.soe_than.movietalkies.ui.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.soe_than.movietalkies.data.Vo.*
import com.example.soe_than.movietalkies.data.repository.MoviesRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MovieViewModel @Inject constructor(var moviesRepository: MoviesRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        moviesRepository.onClear()
        compositeDisposable.clear()
    }

    fun getNowShowingMovies() {

        compositeDisposable.add(moviesRepository.getNowShowingMovies().subscribeOn(Schedulers.io())
                .subscribe({
                    _nowShowingResultLiveData.postValue(it
                    )
                }, { t: Throwable ->
                    Log.i("error", t.message)
                }))
    }

    fun getPopularMovies() {
        compositeDisposable.add(moviesRepository.getPopularMovies().subscribeOn(Schedulers.io())
                .subscribe({
                    _popularResultLiveData.postValue(it
                    )
                }, { t: Throwable ->
                    Log.i("error", t.message)
                }))
    }

    fun getTopRatedMovies() {
        compositeDisposable.add(moviesRepository.getTopRatedMovies().subscribeOn(Schedulers.io())
                .subscribe({
                    _topRatedResultLiveData.postValue(it
                    )
                }, { t: Throwable ->
                    Log.i("error", t.message)
                }))
    }

    fun getUpComingMovies() {
        compositeDisposable.add(moviesRepository.getUpComingMovies().subscribeOn(Schedulers.io())
                .subscribe({
                    _upcomingResultLiveData.postValue(it
                    )
                }, { t: Throwable ->
                    Log.i("error", t.message)
                }))
    }

    private val _searchResultLiveData = MutableLiveData<List<SearchVo>>()
    val searchResultLiveData: LiveData<List<SearchVo>>
        get() = _searchResultLiveData

    private val _nowShowingResultLiveData = MutableLiveData<List<NowShowingVo>>()
    val nowShowingResultLiveData: LiveData<List<NowShowingVo>>
        get() = _nowShowingResultLiveData

    private val _upcomingResultLiveData = MutableLiveData<List<UpComingVo>>()
    val upcomingResultLiveData: LiveData<List<UpComingVo>>
        get() = _upcomingResultLiveData

    private val _topRatedResultLiveData = MutableLiveData<List<TopRatedVo>>()
    val topRatedResultLiveData: LiveData<List<TopRatedVo>>
        get() = _topRatedResultLiveData

    private val _popularResultLiveData = MutableLiveData<List<PopularVo>>()
    val popularResultLiveData: LiveData<List<PopularVo>>
        get() = _popularResultLiveData

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

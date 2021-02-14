package com.example.soe_than.movietalkies.ui.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.soe_than.movietalkies.data.Vo.FavouriteVo
import com.example.soe_than.movietalkies.data.repository.MoviesRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FavouriteViewModel @Inject constructor(val moviesRepository: MoviesRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _favouriteResultLiveData = MutableLiveData<List<FavouriteVo>>()
    val favouriteResultLiveData: LiveData<List<FavouriteVo>>
        get() = _favouriteResultLiveData

    override fun onCleared() {
        compositeDisposable.clear()
        moviesRepository.onClear()
    }

    fun getFavouriteMovies() {
        compositeDisposable.add(
            moviesRepository.getFavourites().subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        _favouriteResultLiveData.postValue(
                            it
                        )
                    },
                    { t: Throwable ->
                        Log.i("error", t.message)
                    }
                )
        )
    }
}

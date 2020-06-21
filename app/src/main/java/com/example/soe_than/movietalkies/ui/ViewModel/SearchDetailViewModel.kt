package com.example.soe_than.movietalkies.ui.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.soe_than.movietalkies.data.Vo.FavouriteVo
import com.example.soe_than.movietalkies.data.Vo.MovieDetailVo
import com.example.soe_than.movietalkies.data.Vo.TrailerVo
import com.example.soe_than.movietalkies.data.repository.MoviesRepository
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchDetailViewModel @Inject constructor(private val moviesRepository: MoviesRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    private val _movieDetailResultLiveData = MutableLiveData<MovieDetailVo>()
    val movieDetailResultLiveData: LiveData<MovieDetailVo>
        get() = _movieDetailResultLiveData

    fun getMovieDetails(id: Int) {
        compositeDisposable.add(moviesRepository.getSearchMovieDetails(id).subscribeOn(Schedulers.io())
                .subscribe({
                    _movieDetailResultLiveData.postValue(it
                    )
                }, { t: Throwable ->
                    Log.i("error", t.message)
                }))
    }

    fun getTrailers(id: Int): LiveData<List<TrailerVo>>? = moviesRepository.getTrailers(id)

    fun checkedFavourite(id: Int): Single<Int> = moviesRepository.checkedFavouriteMovie(id!!)

    fun favouriteStatus(movieDetailVo: MovieDetailVo, checked: Boolean): Completable {
        val favourite = FavouriteVo(movieDetailVo.id, movieDetailVo.poster_path, movieDetailVo.overview, movieDetailVo.title, movieDetailVo.release_date, movieDetailVo.vote_average, movieDetailVo.backdrop_path, movieDetailVo.genreids.map { it.id }.toList(), movieDetailVo.original_language)
        if (checked) {
            return Completable.fromAction {
                moviesRepository.addFavouriteMovie(favourite)
            }
        } else {
            return Completable.fromAction {

                moviesRepository.removeFavouriteMovie(favourite)
            }
        }
    }
}

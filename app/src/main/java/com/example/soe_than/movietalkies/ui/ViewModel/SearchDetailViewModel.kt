package com.example.soe_than.movietalkies.ui.ViewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.example.soe_than.movietalkies.data.Vo.FavouriteVo
import com.example.soe_than.movietalkies.data.Vo.MovieDetailVo
import com.example.soe_than.movietalkies.data.Vo.NowShowingVo
import com.example.soe_than.movietalkies.data.Vo.TrailerVo
import com.example.soe_than.movietalkies.data.repository.MoviesRepository
import io.reactivex.Completable
import io.reactivex.Single

class SearchDetailViewModel(val moviesRepository: MoviesRepository, var id: Int) : ViewModel() {

    fun getMovieDetails(): LiveData<MovieDetailVo> = moviesRepository.getSearchMovieDetails(id)


    fun getTrailers(): LiveData<List<TrailerVo>>? = moviesRepository.getTrailers(id)


    fun checkedFavourite(): Single<Int> = moviesRepository.checkedFavouriteMovie(id!!)


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
package com.example.soe_than.movietalkies.ui.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.soe_than.movietalkies.data.Vo.FavouriteVo
import com.example.soe_than.movietalkies.data.Vo.MovieDetailVo
import com.example.soe_than.movietalkies.data.Vo.NowShowingVo
import com.example.soe_than.movietalkies.data.Vo.TrailerVo
import com.example.soe_than.movietalkies.data.repository.MoviesRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class SearchDetailViewModel @Inject constructor( val moviesRepository: MoviesRepository) : ViewModel() {

    fun getMovieDetails(id:Int): LiveData<MovieDetailVo> = moviesRepository.getSearchMovieDetails(id)


    fun getTrailers(id:Int): LiveData<List<TrailerVo>>? = moviesRepository.getTrailers(id)


    fun checkedFavourite(id:Int): Single<Int> = moviesRepository.checkedFavouriteMovie(id!!)


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
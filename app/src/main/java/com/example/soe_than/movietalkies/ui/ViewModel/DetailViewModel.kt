package com.example.soe_than.movietalkies.ui.ViewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import android.view.View
import com.example.soe_than.movietalkies.data.Vo.*
import com.example.soe_than.movietalkies.data.repository.MoviesRepository
import io.reactivex.Completable
import io.reactivex.Flowable

class DetailViewModel(val moviesRepository: MoviesRepository, var id: Int) : ViewModel() {


    fun getPopularMovieDetails(): LiveData<PopularVo> {
        return moviesRepository.getPopularMovieDetails(id)
    }

    fun getNowShowingMovieDetails(): LiveData<NowShowingVo> {
        return moviesRepository.getNowShowingMovieDetail(id)

    }

    fun getTopRatedMovieDetails(): LiveData<TopRatedVo> {
        return moviesRepository.getTopRatedMovieDetails(id)

    }

    fun getUpComingMovieDetails(): LiveData<UpComingVo> {
        return moviesRepository.getUpComingMovieDetails(id)

    }

    fun getTrailers(): LiveData<List<TrailerVo>>? {
        return moviesRepository.getTrailers(id)
    }

    fun getFavouriteMovieDetails(): LiveData<FavouriteVo> {
        return moviesRepository.getFavouriteMovieDetails(id)
    }

    fun addFavouriteMovie(upComingVo: UpComingVo): Completable {
        return Completable.fromAction {
            var favourite = FavouriteVo(upComingVo.id, upComingVo.posterPath, upComingVo.overview, upComingVo.title, upComingVo.releasedDate, upComingVo.voteAverage, upComingVo.backdrop_path)
            moviesRepository.addFavouriteMovie(favourite)
        }
    }

    fun addFavouriteMovie(topRatedVo: TopRatedVo): Completable {
        return Completable.fromAction {
            var favourite = FavouriteVo(topRatedVo.id, topRatedVo.posterPath, topRatedVo.overview, topRatedVo.title, topRatedVo.releasedDate, topRatedVo.voteAverage, topRatedVo.backdrop_path)
            moviesRepository.addFavouriteMovie(favourite)

        }


    }

    fun addFavouriteMovie(popularVo: PopularVo): Completable {
        return Completable.fromAction {
            var favourite = FavouriteVo(popularVo.id, popularVo.posterPath, popularVo.overview, popularVo.title, popularVo.releasedDate, popularVo.voteAverage, popularVo.backdrop_path)
            moviesRepository.addFavouriteMovie(favourite)

        }

    }

    fun addFavouriteMovie(nowShowingVo: NowShowingVo): Completable {
        return Completable.fromAction {
            val favourite = FavouriteVo(nowShowingVo.id, nowShowingVo.posterPath, nowShowingVo.overview, nowShowingVo.title, nowShowingVo.releasedDate, nowShowingVo.voteAverage, nowShowingVo.backdrop_path)
            moviesRepository.addFavouriteMovie(favourite)

        }
    }

    fun removeFavouriteMovie(favouriteVo: FavouriteVo): Completable {
        return Completable.fromAction {
            moviesRepository.removeFavouriteMovie(favouriteVo)
        }

    }

    fun checkedFavourite():Flowable<Int>
    {
        return  moviesRepository.checkedFavouriteMovie(id)
    }


}
package com.example.soe_than.movietalkies.ui.ViewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import android.view.View
import com.example.soe_than.movietalkies.data.Vo.*
import com.example.soe_than.movietalkies.data.repository.MoviesRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

class DetailViewModel(val moviesRepository: MoviesRepository, var id: Int) : ViewModel() {



    fun getPopularMovieDetails() = moviesRepository.getPopularMovieDetails(id)

    fun getNowShowingMovieDetails() = moviesRepository.getNowShowingMovieDetail(id)



    fun getTopRatedMovieDetails()= moviesRepository.getTopRatedMovieDetails(id)

    fun getUpComingMovieDetails()
        = moviesRepository.getUpComingMovieDetails(id)



    fun getTrailers() = moviesRepository.getTrailers(id) as LiveData<List<TrailerVo>>


    fun getFavouriteMovieDetails()= moviesRepository.getFavouriteMovieDetails(id)


//    fun addFavouriteMovie(upComingVo: UpComingVo): Completable {
//        return Completable.fromAction {
//            var favourite = FavouriteVo(upComingVo.id, upComingVo.posterPath, upComingVo.overview, upComingVo.title, upComingVo.releasedDate, upComingVo.voteAverage, upComingVo.backdrop_path)
//            moviesRepository.addFavouriteMovie(favourite)
//        }
//    }
//
//    fun addFavouriteMovie(topRatedVo: TopRatedVo): Completable {
//        return Completable.fromAction {
//            var favourite = FavouriteVo(topRatedVo.id, topRatedVo.posterPath, topRatedVo.overview, topRatedVo.title, topRatedVo.releasedDate, topRatedVo.voteAverage, topRatedVo.backdrop_path)
//            moviesRepository.addFavouriteMovie(favourite)
//
//        }
//
//
//    }
//
//    fun addFavouriteMovie(popularVo: PopularVo): Completable {
//        return Completable.fromAction {
//            var favourite = FavouriteVo(popularVo.id, popularVo.posterPath, popularVo.overview, popularVo.title, popularVo.releasedDate, popularVo.voteAverage, popularVo.backdrop_path)
//            moviesRepository.addFavouriteMovie(favourite)
//
//        }
//
//    }
//
//    fun addFavouriteMovie(nowShowingVo: NowShowingVo): Completable {
//        return Completable.fromAction {
//            val favourite = FavouriteVo(nowShowingVo.id, nowShowingVo.posterPath, nowShowingVo.overview, nowShowingVo.title, nowShowingVo.releasedDate, nowShowingVo.voteAverage, nowShowingVo.backdrop_path)
//            moviesRepository.addFavouriteMovie(favourite)
//
//        }
//    }
//
//    fun removeFavouriteMovie(favouriteVo: FavouriteVo): Completable {
//        return Completable.fromAction {
//            moviesRepository.removeFavouriteMovie(favouriteVo)
//        }
//
//    }

    fun checkedFavourite(): Single<Int> = moviesRepository.checkedFavouriteMovie(id!!)


    fun favouriteStatus(nowShowingVo: NowShowingVo, checked: Boolean): Completable {

        Log.i("DetailViewModel","${nowShowingVo.genreids.get(1)}")
        val favourite = FavouriteVo(nowShowingVo.id, nowShowingVo.posterPath, nowShowingVo.overview, nowShowingVo.title, nowShowingVo.releasedDate, nowShowingVo.voteAverage, nowShowingVo.backdrop_path,nowShowingVo.genreids)

        
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

    fun favouriteStatus(upComingVo: UpComingVo, checked: Boolean): Completable {
        val favourite = FavouriteVo(upComingVo.id, upComingVo.posterPath, upComingVo.overview, upComingVo.title, upComingVo.releasedDate, upComingVo.voteAverage, upComingVo.backdrop_path,upComingVo.genreids)
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

    fun favouriteStatus(topRatedVo: TopRatedVo, checked: Boolean): Completable {
        val favourite = FavouriteVo(topRatedVo.id, topRatedVo.posterPath, topRatedVo.overview, topRatedVo.title, topRatedVo.releasedDate, topRatedVo.voteAverage, topRatedVo.backdrop_path,topRatedVo.genreids)
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

    fun favouriteStatus(popularVo: PopularVo, checked: Boolean): Completable {
        val favourite = FavouriteVo(popularVo.id, popularVo.posterPath, popularVo.overview, popularVo.title, popularVo.releasedDate, popularVo.voteAverage, popularVo.backdrop_path,popularVo.genreids)
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

    fun favouriteStatus(favouriteVo: FavouriteVo, checked: Boolean): Completable {

        if (checked) {
            return Completable.fromAction {
                moviesRepository.addFavouriteMovie(favouriteVo)
            }
        } else {
            return Completable.fromAction {

                moviesRepository.removeFavouriteMovie(favouriteVo)
            }
        }
    }


}
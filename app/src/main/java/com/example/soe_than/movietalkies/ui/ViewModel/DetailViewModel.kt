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

    fun getUpComingMovieDetails() = moviesRepository.getUpComingMovieDetails(id)



fun getTrailers(): LiveData<List<TrailerVo>>? {
    return moviesRepository.getTrailers(id)
}

    fun getFavouriteMovieDetails()= moviesRepository.getFavouriteMovieDetails(id)




    fun checkedFavourite(): Single<Int> = moviesRepository.checkedFavouriteMovie(id!!)


    fun favouriteStatus(nowShowingVo: NowShowingVo, checked: Boolean): Completable {

        Log.i("DetailViewModel","${nowShowingVo.genreids.get(1)}")
        val favourite = FavouriteVo(nowShowingVo.id, nowShowingVo.posterPath, nowShowingVo.overview, nowShowingVo.title, nowShowingVo.releasedDate, nowShowingVo.voteAverage, nowShowingVo.backdrop_path,nowShowingVo.genreids,nowShowingVo.originalLang)

        
       return  if (checked) {
             Completable.fromAction {
                moviesRepository.addFavouriteMovie(favourite)
            }
        } else {
             Completable.fromAction {

                moviesRepository.removeFavouriteMovie(favourite)
            }
        }


    }

    fun favouriteStatus(upComingVo: UpComingVo, checked: Boolean): Completable {
        val favourite = FavouriteVo(upComingVo.id, upComingVo.posterPath, upComingVo.overview, upComingVo.title, upComingVo.releasedDate, upComingVo.voteAverage, upComingVo.backdrop_path,upComingVo.genreids,upComingVo.originalLang)
       return  if (checked) {
             Completable.fromAction {
                moviesRepository.addFavouriteMovie(favourite)
            }
        } else {
             Completable.fromAction {

                moviesRepository.removeFavouriteMovie(favourite)
            }
        }
    }

    fun favouriteStatus(topRatedVo: TopRatedVo, checked: Boolean): Completable {
        val favourite = FavouriteVo(topRatedVo.id, topRatedVo.posterPath, topRatedVo.overview, topRatedVo.title, topRatedVo.releasedDate, topRatedVo.voteAverage, topRatedVo.backdrop_path,topRatedVo.genreids,topRatedVo.originalLang)
       return  if (checked) {
             Completable.fromAction {
                moviesRepository.addFavouriteMovie(favourite)
            }
        } else {
             Completable.fromAction {

                moviesRepository.removeFavouriteMovie(favourite)
            }
        }
    }

    fun favouriteStatus(popularVo: PopularVo, checked: Boolean): Completable {
        val favourite = FavouriteVo(popularVo.id, popularVo.posterPath, popularVo.overview, popularVo.title, popularVo.releasedDate, popularVo.voteAverage, popularVo.backdrop_path,popularVo.genreids,popularVo.originalLang)
        return if (checked) {
             Completable.fromAction {
                moviesRepository.addFavouriteMovie(favourite)
            }
        } else {
             Completable.fromAction {

                moviesRepository.removeFavouriteMovie(favourite)
            }
        }
    }

    fun favouriteStatus(favouriteVo: FavouriteVo, checked: Boolean): Completable {

        return if (checked) {
             Completable.fromAction {
                moviesRepository.addFavouriteMovie(favouriteVo)
            }
        } else {
             Completable.fromAction {

                moviesRepository.removeFavouriteMovie(favouriteVo)
            }
        }
    }


}
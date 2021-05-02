package com.soethan.movietalkies.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import android.util.Log
import com.soethan.movietalkies.BuildConfig
import com.soethan.movietalkies.api.ApiService
import com.soethan.movietalkies.data.Vo.*
import com.soethan.movietalkies.data.local.Daos.MovieDao
import com.soethan.movietalkies.data.response.SearchResponse
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRepository @Inject constructor(private val movieDao: MovieDao, private val apiService: ApiService) {

    var trailerData: MutableLiveData<List<TrailerVo>> = MutableLiveData()
    var favouriteData: MutableLiveData<List<FavouriteVo>> = MutableLiveData()
    var movieDetailData: MutableLiveData<MovieDetailVo> = MutableLiveData()


    fun getNowShowingMovies(): LiveData<List<NowShowingVo>> {



        apiService.getNowShowingMovies(BuildConfig.API_KEY)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .map { nowShowingResponse ->
                    nowShowingResponse.nowShowingVo }
                .subscribe({ nowShowingList: List<NowShowingVo> -> movieDao.saveAllNowShowingMovies(nowShowingList) },
                        { t: Throwable -> Log.i("error: %s", t.message ?: "Something went wrong") })

        return movieDao.getAllNowShowingMovies()
    }

    fun getPopularMovies(): LiveData<List<PopularVo>> {
        apiService.getPopularMovies(BuildConfig.API_KEY)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .map { popularResponse -> popularResponse.popularVo }
                .subscribe({ popularList: List<PopularVo> -> movieDao.saveAllPopularMovies(popularList) },
                        { t: Throwable -> Log.i("error: %s", t.message ?: "Something went wrong") })

        return movieDao.getAllPopularMovies()
    }

    fun getTopRatedMovies(): LiveData<List<TopRatedVo>> {
        apiService.getTopRatedMovies(BuildConfig.API_KEY)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .map { topRatedResponse -> topRatedResponse.topRatedVo }
                .subscribe({ topRatedList: List<TopRatedVo> -> movieDao.saveAllTopRatedMovies(topRatedList) },
                        { t: Throwable -> Log.i("error: %s", t.message ?: "Something went wrong") })

        return movieDao.getAllTopRatedMovies()
    }

    fun getUpComingMovies(): LiveData<List<UpComingVo>> {
        apiService.getUpComingMovies(BuildConfig.API_KEY)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .map { upComingResponse -> upComingResponse.upComingVo }
                .subscribe({ upComingList: List<UpComingVo> ->
                    movieDao.saveAllUpComingMovies(upComingList)
                },
                        { t: Throwable -> Log.i("error: %s", t.message ?: "Something went wrong") })

        return movieDao.getAllUpComingMovies()
    }

    fun getTrailers(id: Int): LiveData<List<TrailerVo>>? {

        apiService.getTrailers(id, BuildConfig.API_KEY)
                .subscribeOn(Schedulers.io()).toObservable().map { trailerResponse ->
                    trailerResponse.results
                }.subscribe({ trailerList: List<TrailerVo> -> trailerData.postValue(trailerList) }, { t: Throwable -> Log.i("error: %s", t.message ?: "Something went wrong") })

        return trailerData
//        return if (Utility.isNetworkAvailable()) {
//            apiService.getTrailers(id, API_KEY)
//                    .subscribeOn(Schedulers.io()).toObservable().map { trailerResponse ->
//                        trailerResponse.results
//                    }
//                    .subscribe({ trailerList: List<TrailerVo> -> trailerData.postValue(trailerList) }, { t: Throwable -> Log.i("error: %s", t.message ?: "Something went wrong") })
//            trailerData
//        } else {
//            null
//        }

    }


    fun getSearchMovie(query: String): Single<SearchResponse> {
        return apiService.getSearchResult(BuildConfig.API_KEY, query)
    }


    fun getFavourites(): LiveData<List<FavouriteVo>> {
        movieDao.getAllFavouriteMovies().subscribeOn(Schedulers.io()).toObservable().map { t: List<FavouriteVo> -> t }
                .subscribe({ favouriteList -> favouriteData.postValue(favouriteList) }, { t: Throwable -> Log.i("error: %s", t.message ?: "Something went wrong") })

        return favouriteData
    }


    fun getSearchMovieDetails(id: Int): LiveData<MovieDetailVo> {
        apiService.getMovieDetail(id, BuildConfig.API_KEY)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .subscribe({ movieDetail: MovieDetailVo ->
                    movieDetailData.postValue(movieDetail)
                }, { t: Throwable ->
                    Log.i("error: %s", t.message ?: "Something went wrong")
                })
        return movieDetailData
    }

    fun addFavouriteMovie(favouriteVo: FavouriteVo) {
        movieDao.saveFavouriteMovies(favouriteVo)
    }

    fun removeFavouriteMovie(favouriteVo: FavouriteVo) {
        movieDao.clearFavoutireMoive(favouriteVo)
    }

    fun getPopularMovieDetails(id: Int): LiveData<PopularVo> {
        return movieDao.getPopularMovieById(id.toString())
    }

    fun getTopRatedMovieDetails(id: Int): LiveData<TopRatedVo> {
        return movieDao.getTopRatedMovieByID(id.toString())
    }

    fun getNowShowingMovieDetail(id: Int): LiveData<NowShowingVo> {
        return movieDao.getNowShowingMovieById(id.toString())
    }

    fun getUpComingMovieDetails(id: Int): LiveData<UpComingVo> {
        return movieDao.getUpComingMovieById(id.toString())
    }

    fun getFavouriteMovieDetails(id: Int): LiveData<FavouriteVo> {
        return movieDao.getFavouriteMovieById(id.toString())
    }

    fun checkedFavouriteMovie(id: Int): Single<Int> {

        return movieDao.isFavouriteMovie(id.toString()!!)
    }


}
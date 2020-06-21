package com.example.soe_than.movietalkies.data.repository

import android.util.Log
import androidx.lifecycle.*
import com.example.soe_than.movietalkies.Utils.API_KEY
import com.example.soe_than.movietalkies.api.ApiService
import com.example.soe_than.movietalkies.data.Vo.*
import com.example.soe_than.movietalkies.data.local.Daos.MovieDao
import com.example.soe_than.movietalkies.data.response.SearchResponse
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRepository @Inject constructor(private val movieDao: MovieDao, private val apiService: ApiService) {

    var trailerData: MutableLiveData<List<TrailerVo>> = MutableLiveData()
    var favouriteData: MutableLiveData<List<FavouriteVo>> = MutableLiveData()
    var movieDetailData: MutableLiveData<MovieDetailVo> = MutableLiveData()

    private val compositeDisposable = CompositeDisposable()

    fun onClear() {
        compositeDisposable.clear()
    }

    fun getNowShowingMovies(): Observable<List<NowShowingVo>> {

        compositeDisposable.add(apiService.getNowShowingMovies(API_KEY)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .map { nowShowingResponse ->
                    nowShowingResponse.nowShowingVo
                }
                .subscribe({ nowShowingList: List<NowShowingVo> -> movieDao.saveAllNowShowingMovies(nowShowingList) },
                        { t: Throwable -> Log.i("error: %s", t.message) }))

        return movieDao.getAllNowShowingMovies()
    }

    fun getPopularMovies(): Observable<List<PopularVo>> {
        compositeDisposable.add(apiService.getPopularMovies(API_KEY)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .map { popularResponse -> popularResponse.popularVo }
                .subscribe({ popularList: List<PopularVo> -> movieDao.saveAllPopularMovies(popularList) },
                        { t: Throwable -> Log.i("error: %s", t.message) }))

        return movieDao.getAllPopularMovies()
    }

    fun getTopRatedMovies(): Observable<List<TopRatedVo>> {
        compositeDisposable.add(apiService.getTopRatedMovies(API_KEY)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .map { topRatedResponse -> topRatedResponse.topRatedVo }
                .subscribe({ topRatedList: List<TopRatedVo> -> movieDao.saveAllTopRatedMovies(topRatedList) },
                        { t: Throwable -> Log.i("error: %s", t.message) }))

        return movieDao.getAllTopRatedMovies()
    }

    fun getUpComingMovies(): Observable<List<UpComingVo>> {
        compositeDisposable.add(apiService.getUpComingMovies(API_KEY)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .map { upComingResponse -> upComingResponse.upComingVo }
                .subscribe({ upComingList: List<UpComingVo> ->
                    movieDao.saveAllUpComingMovies(upComingList)
                },
                        { t: Throwable -> Log.i("error: %s", t.message) }))

        return movieDao.getAllUpComingMovies()
    }

    fun getTrailers(id: Int): LiveData<List<TrailerVo>>? {

        compositeDisposable.add(apiService.getTrailers(id, API_KEY)
                .subscribeOn(Schedulers.io()).toObservable().map { trailerResponse ->
                    trailerResponse.results
                }.subscribe({ trailerList: List<TrailerVo> -> trailerData.postValue(trailerList) }, { t: Throwable -> Log.i("error: %s", t.message) }))

        return trailerData
//        return if (Utility.isNetworkAvailable()) {
//            apiService.getTrailers(id, API_KEY)
//                    .subscribeOn(Schedulers.io()).toObservable().map { trailerResponse ->
//                        trailerResponse.results
//                    }
//                    .subscribe({ trailerList: List<TrailerVo> -> trailerData.postValue(trailerList) }, { t: Throwable -> Log.i("error: %s", t.message) })
//            trailerData
//        } else {
//            null
//        }
    }

    fun getSearchMovie(query: String): Single<SearchResponse> {
        return apiService.getSearchResult(API_KEY, query)
    }

    fun getFavourites() = movieDao.getAllFavouriteMovies()

    fun getSearchMovieDetails(id: Int): Single<MovieDetailVo> {

        return apiService.getMovieDetail(id, API_KEY)
//        compositeDisposable.add(apiService.getMovieDetail(id, API_KEY)
//                .subscribeOn(Schedulers.io())
//                .toObservable()
//                .subscribe({ movieDetail: MovieDetailVo ->
//                    movieDetailData.postValue(movieDetail)
//                }, { t: Throwable ->
//                    Log.i("error: %s", t.message)
//                }))
//        return movieDetailData
    }

    fun addFavouriteMovie(favouriteVo: FavouriteVo) {
        movieDao.saveFavouriteMovies(favouriteVo)
    }

    fun removeFavouriteMovie(favouriteVo: FavouriteVo) {
        movieDao.clearFavoutireMoive(favouriteVo)
    }

    fun getPopularMovieDetails(id: Int): Single<PopularVo> {
        return movieDao.getPopularMovieById(id.toString())
    }

    fun getTopRatedMovieDetails(id: Int): Single<TopRatedVo> {
        return movieDao.getTopRatedMovieByID(id.toString())
    }

    fun getNowShowingMovieDetail(id: Int): Single<NowShowingVo> {
        return movieDao.getNowShowingMovieById(id.toString())
    }

    fun getUpComingMovieDetails(id: Int): Single<UpComingVo> {
        return movieDao.getUpComingMovieById(id.toString())
    }

    fun getFavouriteMovieDetails(id: Int): Single<FavouriteVo> {
        return movieDao.getFavouriteMovieById(id.toString())
    }

    fun checkedFavouriteMovie(id: Int): Single<Int> {

        return movieDao.isFavouriteMovie(id.toString())
    }
}

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
class MoviesRepository @Inject constructor(private val movieDao: MovieDao, private val apiService: ApiService) : RepositoryInterface {

    var trailerData: MutableLiveData<List<TrailerVo>> = MutableLiveData()

    private val compositeDisposable = CompositeDisposable()

    override fun onClear() {
        compositeDisposable.clear()
    }

    override fun getNowShowingMovies(): Observable<List<NowShowingVo>> {
        compositeDisposable.add(
            apiService.getNowShowingMovies(API_KEY)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .map { nowShowingResponse ->
                    nowShowingResponse.nowShowingVo
                }
                .subscribe(
                    { nowShowingList: List<NowShowingVo> -> movieDao.saveAllNowShowingMovies(nowShowingList) },
                    { t: Throwable -> Log.i("error: %s", t.message) }
                )
        )

        return movieDao.getAllNowShowingMovies()
    }

    override fun getPopularMovies(): Observable<List<PopularVo>> {
        compositeDisposable.add(
            apiService.getPopularMovies(API_KEY)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .map { popularResponse -> popularResponse.popularVo }
                .subscribe(
                    { popularList: List<PopularVo> -> movieDao.saveAllPopularMovies(popularList) },
                    { t: Throwable -> Log.i("error: %s", t.message) }
                )
        )

        return movieDao.getAllPopularMovies()
    }
//
//    fun getPopularMovies(): Observable<List<PopularVo>> {

//    }

    override fun getTopRatedMovies(): Observable<List<TopRatedVo>> {
        compositeDisposable.add(
            apiService.getTopRatedMovies(API_KEY)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .map { topRatedResponse -> topRatedResponse.topRatedVo }
                .subscribe(
                    { topRatedList: List<TopRatedVo> -> movieDao.saveAllTopRatedMovies(topRatedList) },
                    { t: Throwable -> Log.i("error: %s", t.message) }
                )
        )

        return movieDao.getAllTopRatedMovies()
    }

    override fun getUpComingMovies(): Observable<List<UpComingVo>> {
        compositeDisposable.add(
            apiService.getUpComingMovies(API_KEY)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .map { upComingResponse -> upComingResponse.upComingVo }
                .subscribe(
                    { upComingList: List<UpComingVo> ->
                        movieDao.saveAllUpComingMovies(upComingList)
                    },
                    { t: Throwable -> Log.i("error: %s", t.message) }
                )
        )

        return movieDao.getAllUpComingMovies()
    }

    override fun getTrailers(id: Int): LiveData<List<TrailerVo>> {
        compositeDisposable.add(
            apiService.getTrailers(id, API_KEY)
                .subscribeOn(Schedulers.io()).toObservable().map { trailerResponse ->
                    trailerResponse.results
                }.subscribe({ trailerList: List<TrailerVo> -> trailerData.postValue(trailerList) }, { t: Throwable -> Log.i("error: %s", t.message) })
        )

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

    override fun getSearchMovie(query: String): Single<SearchResponse> {
        return apiService.getSearchResult(API_KEY, query)
    }

    override fun getFavourites(): Observable<List<FavouriteVo>> {
        return movieDao.getAllFavouriteMovies()
    }

    override fun getSearchMovieDetails(id: Int): Single<MovieDetailVo> {

        return apiService.getMovieDetail(id, API_KEY)
    }

    override fun addFavouriteMovie(favouriteVo: FavouriteVo) {
        movieDao.saveFavouriteMovies(favouriteVo)
    }

    override fun removeFavouriteMovie(favouriteVo: FavouriteVo) {
        movieDao.clearFavoutireMoive(favouriteVo)
    }

    override fun getPopularMovieDetails(id: Int): Single<PopularVo> {
        return movieDao.getPopularMovieById(id.toString())
    }

    override fun getTopRatedMovieDetails(id: Int): Single<TopRatedVo> {
        return movieDao.getTopRatedMovieByID(id.toString())
    }

    override fun getNowShowingMovieDetail(id: Int): Single<NowShowingVo> {
        return movieDao.getNowShowingMovieById(id.toString())
    }

    override fun getUpComingMovieDetails(id: Int): Single<UpComingVo> {
        return movieDao.getUpComingMovieById(id.toString())
    }

    override fun getFavouriteMovieDetails(id: Int): Single<FavouriteVo> {
        return movieDao.getFavouriteMovieById(id.toString())
    }

    override fun checkedFavouriteMovie(id: Int): Single<Int> {

        return movieDao.isFavouriteMovie(id.toString())
    }
}

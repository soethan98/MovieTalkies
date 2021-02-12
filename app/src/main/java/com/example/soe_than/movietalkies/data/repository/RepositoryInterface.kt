package com.example.soe_than.movietalkies.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.soe_than.movietalkies.Utils.API_KEY
import com.example.soe_than.movietalkies.data.Vo.*
import com.example.soe_than.movietalkies.data.response.SearchResponse
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

interface RepositoryInterface{
    fun onClear()
    fun getNowShowingMovies():Observable<List<NowShowingVo>>
    fun getPopularMovies():Observable<List<PopularVo>>
    fun getTopRatedMovies():Observable<List<TopRatedVo>>
    fun getUpComingMovies():Observable<List<UpComingVo>>
    fun getTrailers(id:Int):LiveData<List<TrailerVo>>
    fun getSearchMovie(query: String):Single<SearchResponse>
    fun getFavourites():Observable<List<FavouriteVo>>
    fun getSearchMovieDetails(id:Int):Single<MovieDetailVo>
    fun addFavouriteMovie(favouriteVo: FavouriteVo)
    fun removeFavouriteMovie(favouriteVo: FavouriteVo)
    fun getPopularMovieDetails(id: Int): Single<PopularVo>
    fun getTopRatedMovieDetails(id: Int): Single<TopRatedVo>
    fun getNowShowingMovieDetail(id: Int): Single<NowShowingVo>
    fun getUpComingMovieDetails(id: Int): Single<UpComingVo>
    fun getFavouriteMovieDetails(id: Int): Single<FavouriteVo>
    fun checkedFavouriteMovie(id: Int): Single<Int>

}


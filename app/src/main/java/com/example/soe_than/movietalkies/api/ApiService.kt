package com.example.soe_than.movietalkies.api

import com.example.soe_than.movietalkies.data.Vo.MovieDetailVo
import com.example.soe_than.movietalkies.data.response.*
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey: String): Single<PopularResponse>

    @GET("movie/top_rated")
    fun getTopRatedMovies(@Query("api_key") apiKey: String): Single<TopRatedResponse>

    @GET("movie/upcoming")
    fun getUpComingMovies(@Query("api_key") apiKey: String): Single<UpcomingResponse>

    @GET("movie/now_playing")
    fun getNowShowingMovies(@Query("api_key") apiKey: String): Single<NowShowingResponse>

    @GET("movie/{id}/videos")
    fun getTrailers(@Path("id") id: Int, @Query("api_key") apiKey: String): Single<TrailerResponse>

    @GET("search/movie")
    fun getSearchResult(
        @Query("api_key") apiKey: String,
        @Query("query") keyword: String
    ): Single<SearchResponse>

    @GET("movie/{id}")
    fun getMovieDetail(
        @Path("id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Single<MovieDetailVo>
}

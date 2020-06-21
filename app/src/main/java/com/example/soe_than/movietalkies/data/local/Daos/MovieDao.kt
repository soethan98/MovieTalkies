package com.example.soe_than.movietalkies.data.local.Daos

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.annotation.MainThread
import com.example.soe_than.movietalkies.ViewHolder.PopularViewHolder
import com.example.soe_than.movietalkies.data.Vo.*
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single


@Dao
interface MovieDao {

    @Query("SELECT * FROM nowshowing")
    fun getAllNowShowingMovies(): Observable<List<NowShowingVo>>

    @Query("SELECT * FROM popular")
    fun getAllPopularMovies(): Observable<List<PopularVo>>

    @Query("SELECT * FROM toprated")
    fun getAllTopRatedMovies(): Observable<List<TopRatedVo>>

    @Query("SELECT * FROM upcoming")
    fun getAllUpComingMovies(): Observable<List<UpComingVo>>

    @Query("SELECT * FROM favourite")
    fun getAllFavouriteMovies(): Observable<List<FavouriteVo>>

    @Delete
    fun clearFavoutireMoive(movies: FavouriteVo)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllNowShowingMovies(movies: List<NowShowingVo>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllPopularMovies(movies: List<PopularVo>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllTopRatedMovies(movies: List<TopRatedVo>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllUpComingMovies(movies: List<UpComingVo>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveFavouriteMovies(favouriteVo: FavouriteVo)

    @Query("SELECT * FROM nowshowing WHERE id=:id")
    fun getNowShowingMovieById(id: String): Single<NowShowingVo>

    @Query("SELECT * FROM popular WHERE id=:id")
    fun getPopularMovieById(id: String): Single<PopularVo>

    @Query("SELECT * FROM toprated WHERE id=:id")
    fun getTopRatedMovieByID(id: String): Single<TopRatedVo>

    @Query("SELECT * FROM upcoming WHERE id=:id")
    fun getUpComingMovieById(id: String): Single<UpComingVo>

    @Query("SELECT * FROM favourite WHERE id=:id")
    fun getFavouriteMovieById(id: String): Single<FavouriteVo>


    @Query("SELECT COUNT(*) FROM favourite WHERE id =:id")
    fun isFavouriteMovie(id: String): Single<Int>
}
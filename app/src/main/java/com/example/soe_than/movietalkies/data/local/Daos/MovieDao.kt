package com.example.soe_than.movietalkies.data.local.Daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.example.soe_than.movietalkies.ViewHolder.PopularViewHolder
import com.example.soe_than.movietalkies.data.Vo.NowShowingVo
import com.example.soe_than.movietalkies.data.Vo.PopularVo
import com.example.soe_than.movietalkies.data.Vo.TopRatedVo
import com.example.soe_than.movietalkies.data.Vo.UpComingVo


@Dao
interface MovieDao {

    @Query("SELECT * FROM nowshowing")
    fun getAllNowShowingMovies(): LiveData<List<NowShowingVo>>

    @Query("SELECT * FROM popular")
    fun getAllPopularMovies(): LiveData<List<PopularVo>>

    @Query("SELECT * FROM toprated")
    fun getAllTopRatedMovies(): LiveData<List<TopRatedVo>>

    @Query("SELECT * FROM upcoming")
    fun getAllUpComingMovies(): LiveData<List<UpComingVo>>

    @Delete
    fun clearNowShowingMovies(movies: List<NowShowingVo>)

    @Delete
    fun clearPopularMovies(movies: List<PopularVo>)

    @Delete
    fun clearTopRatedMovies(movies: List<TopRatedVo>)

    @Delete
    fun clearUpComingMovies(movies: List<UpComingVo>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllNowShowingMovies(movies: List<NowShowingVo>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllPopularMovies(movies: List<PopularVo>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllTopRatedMovies(movies: List<TopRatedVo>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllUpComingMovies(movies: List<UpComingVo>)

    @Query("SELECT * FROM nowshowing WHERE id=:id")
    fun getNowShowingMovieById(id: String): LiveData<NowShowingVo>

    @Query("SELECT * FROM popular WHERE id=:id")
    fun getPopularMovieById(id: String): LiveData<PopularVo>

    @Query("SELECT * FROM toprated WHERE id=:id")
    fun getTopRatedMovieByID(id: String): LiveData<TopRatedVo>

    @Query("SELECT * FROM upcoming WHERE id=:id")
    fun getUpComingMovieById(id: String): LiveData<UpComingVo>
}
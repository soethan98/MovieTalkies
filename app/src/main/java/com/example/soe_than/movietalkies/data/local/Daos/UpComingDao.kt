package com.example.soe_than.movietalkies.data.local.Daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.example.soe_than.movietalkies.data.Vo.NowShowingVo
import com.example.soe_than.movietalkies.data.Vo.UpComingVo


@Dao
interface UpComingDao {

    @Query("SELECT * FROM upcoming")
    fun getAllMovies(): LiveData<List<UpComingVo>>

    @Delete
    fun clear(movies: List<UpComingVo>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAll(movies: List<UpComingVo>)

    @Query("SELECT * FROM upcoming WHERE id=:id")
    fun getMovieById(id: String): LiveData<UpComingVo>
}
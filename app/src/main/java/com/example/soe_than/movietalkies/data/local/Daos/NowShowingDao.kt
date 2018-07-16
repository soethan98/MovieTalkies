package com.example.soe_than.movietalkies.data.local.Daos

import android.graphics.Movie
import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.example.soe_than.movietalkies.data.Vo.NowShowingVo
import com.example.soe_than.movietalkies.data.Vo.PopularVo


@Dao
interface NowShowingDao {
    @Query("SELECT * FROM nowshowing")
    fun getAllMovies(): LiveData<List<NowShowingVo>>


    @Delete
    fun clear(movies: List<NowShowingVo>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAll(movies: List<NowShowingVo>)

    @Query("SELECT * FROM nowshowing WHERE id=:id")
    fun getMovieById(id: String):LiveData<NowShowingVo>
}
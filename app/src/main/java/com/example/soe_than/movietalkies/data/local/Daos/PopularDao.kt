package com.example.soe_than.movietalkies.data.local.Daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.example.soe_than.movietalkies.data.Vo.NowShowingVo
import com.example.soe_than.movietalkies.data.Vo.PopularVo


@Dao
interface PopularDao {

    @Query("SELECT * FROM popular")
    fun getAllMovies(): LiveData<List<PopularVo>>

    @Delete
    fun clear(movies: List<PopularVo>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAll(movies: List<PopularVo>)

    @Query("SELECT * FROM popular WHERE id=:id")
    fun getMovieById(id: String): LiveData<PopularVo>
}
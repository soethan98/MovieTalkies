package com.example.soe_than.movietalkies.data.local.Daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.example.soe_than.movietalkies.data.Vo.NowShowingVo
import com.example.soe_than.movietalkies.data.Vo.TopRatedVo


@Dao
interface TopRatedDao {
    @Query("SELECT * FROM toprated")
    fun getAllMovies(): LiveData<List<TopRatedVo>>

    @Delete
    fun clear(movies: List<TopRatedVo>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAll(movies: List<TopRatedVo>)

    @Query("SELECT * FROM toprated WHERE id=:id")
    fun getMovieById(id: String): LiveData<TopRatedVo>
}
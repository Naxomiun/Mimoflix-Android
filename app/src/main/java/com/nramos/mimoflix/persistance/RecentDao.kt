package com.nramos.mimoflix.persistance

import androidx.room.*
import com.nramos.mimoflix.model.moviedb.MovieDB
import com.nramos.mimoflix.model.recentcast.RecentCast
import com.nramos.mimoflix.model.recentmovie.RecentMovie

@Dao
interface RecentDao {

    @Query("SELECT * FROM RecentMovie")
    suspend fun getRecentMovies(): List<RecentMovie>

    @Query("SELECT * FROM RecentCast")
    suspend fun getRecentCast(): List<RecentCast>

    @Query("SELECT COUNT(*) FROM RecentMovie")
    suspend fun getCountRecentMovies(): Int

    @Query("SELECT COUNT(*) FROM RecentCast")
    suspend fun getCountRecentCast(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecentMovie(recentMovie: RecentMovie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecentCast(recentCast: RecentCast)

}
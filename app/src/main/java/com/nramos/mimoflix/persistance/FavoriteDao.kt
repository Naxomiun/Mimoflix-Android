package com.nramos.mimoflix.persistance

import androidx.room.*

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM MovieDB")
    suspend fun getAllMovies(): List<MovieDB>

    @Query("SELECT COUNT(*) FROM MovieDB WHERE id = :id")
    suspend fun checkIfFavorite(id: Int): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movieDB: MovieDB)

    @Delete
    suspend fun deleteMovie(movieDB: MovieDB)

}
package com.nramos.mimoflix.persistance


import androidx.room.*

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM Favorite")
    suspend fun getAllMovies(): List<MovieDB>

    @Query("SELECT COUNT(*) FROM Favorite WHERE id = :id")
    suspend fun checkIfMovieExist(id: String): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movieDB: MovieDB)

    @Delete
    suspend fun deleteMovie(movieDB: MovieDB)

}
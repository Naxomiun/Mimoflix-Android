package com.nramos.mimoflix.repo


import android.util.Log
import com.nramos.mimoflix.api.ApiService
import com.nramos.mimoflix.api.RecommendedProvider
import com.nramos.mimoflix.model.*
import com.nramos.mimoflix.model.movie.Movie
import com.nramos.mimoflix.persistance.FavoriteDao
import com.nramos.mimoflix.persistance.MovieDB

class MoviesRepository(
    private val apiService: ApiService,
    private val roomDao: FavoriteDao,
    private val recommendedProvider: RecommendedProvider
) : BaseRepository() {

    suspend fun getAllFavorites(): List<MovieDB>? {
        return favoritesRoomCall {
            roomDao.getAllMovies()
        }
    }

    suspend fun deleteFavorite(movie: MovieDB) : Boolean? {
        return transactionRoomCall {
            roomDao.deleteMovie(movie)
        }
    }

    suspend fun saveFavorite(movie: MovieDB) : Boolean? {
        return transactionRoomCall {
            roomDao.insertMovie(movie)
        }
    }

    suspend fun checkForId(id: Int) : Boolean? {
        return roomDao.checkIfFavorite(id)>0
    }

    suspend fun getTrailer(id : Int) : Trailer? {
        return trailerSafeCall {
            apiService.getTrailer(id)
        }
    }

    suspend fun getPopularMovies() : List<Movie> {
        return moviesSafeCall {
            apiService.getPopularMovies()
        }
    }

    suspend fun getTrendingMovies() : List<Movie> {
        return moviesSafeCall {
            apiService.getTredingMovies()
        }
    }

    suspend fun searchMovies(query : String) : List<Movie> {
        return moviesSafeCall {
            apiService.searchMovies(query)
        }
    }

    suspend fun getRecommendedMovies() : List<Movie> {
        return moviesSafeCall {
            apiService.getRecommendedMovies()
        }
    }

    suspend fun getRecommendedSeries() : List<Serie>? {
        return seriesSafeCall {
            apiService.getRecommendedSeries()
        }
    }

    suspend fun getMovieImages(id : Int) : List<Backdrop>? {
        return movieImagesSafeCall {
            apiService.getMovieImages(id)
        }
    }

    suspend fun getMovieDetail(id : Int) : MovieDetail? {
        return movieDetailSafeCall {
            apiService.getMovieDetail(id)
        }
    }

    suspend fun getRelatedMovies(id : Int) : List<Movie>? {
        return moviesSafeCall {
            apiService.getRelatedMovies(id)
        }
    }

    suspend fun getSeriesPerGenre(id : Int) : List<Serie>? {
        return if(id == 0) {
            recommendedProvider.getRecommendedSeries
        } else seriesSafeCall { apiService.getSeriesPerGenre(id) }
    }

    suspend fun getMoviesPerGenre(id : Int) : List<Movie> {
        return moviesSafeCall {
            apiService.getMoviesPerGenre(id)
        }
    }

    suspend fun getGenres() : List<Genre>? {
        val mergedGenres : Set<Genre>
        val movieGenres = getGenres{ apiService.getMovieGenres() }
        return movieGenres.toList()
    }


}

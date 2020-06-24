package com.nramos.mimoflix.repo.movies


import com.nramos.mimoflix.api.ApiService
import com.nramos.mimoflix.api.LocalProvider
import com.nramos.mimoflix.model.*
import com.nramos.mimoflix.model.localgenre.LocalGenre
import com.nramos.mimoflix.model.movie.Movie
import com.nramos.mimoflix.persistance.FavoriteDao
import com.nramos.mimoflix.persistance.MovieDB

class MovieRepository(
    private val apiService: ApiService,
    private val roomDao: FavoriteDao,
    private val localProvider: LocalProvider
) : BaseMovieRepository() {

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

    suspend fun getMoviesPerGenre(id : Int) : List<Movie> {
        return moviesSafeCall {
            apiService.getMoviesPerGenre(id)
        }
    }

}

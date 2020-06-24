package com.nramos.mimoflix.repo.movies

import android.util.Log
import com.nramos.mimoflix.extension.safeSubList
import com.nramos.mimoflix.model.*
import com.nramos.mimoflix.model.localgenre.LocalGenre
import com.nramos.mimoflix.model.movie.Movie
import com.nramos.mimoflix.persistance.MovieDB
import retrofit2.Response

open class BaseMovieRepository {

    suspend fun trailerSafeCall(function: suspend() -> Response<TrailerApiResponse>) : Trailer? {
        kotlin.runCatching {
            function.invoke()
        }.onSuccess {
            if(it.isSuccessful) {
                it.body()?.results?.let { trailers ->
                    if(trailers.isNotEmpty()){
                        return trailers[0]
                    }
                }
            }
            else return null
        }
        return null
    }

    suspend fun movieImagesSafeCall(function: suspend() -> Response<ImageResponse>) : List<Backdrop>? {
        kotlin.runCatching {
            function.invoke()
        }.onSuccess {
            return if(it.isSuccessful) it.body()?.images?.safeSubList(0, 5)
            else emptyList()
        }

        return emptyList()
    }

    suspend fun movieDetailSafeCall(function: suspend() -> Response<MovieDetail>) : MovieDetail? {
        runCatching {
            function.invoke()
        }.onSuccess {
            return if(it.isSuccessful) {
                val auxCredits : MutableList<Actor> = mutableListOf()
                val movieDetail = it.body()
                movieDetail.let { movie ->
                    movie?.credits?.cast?.forEach {actor->
                        if(!actor.profileImage.isNullOrEmpty())
                            auxCredits.add(actor)
                    }
                    movie?.credits?.cast = auxCredits
                }
                movieDetail
            } else {
                null
            }
        }
        return null
    }

    suspend fun moviesSafeCall(function: suspend() -> Response<MovieApiResponse>) : List<Movie> {
        runCatching {
            function.invoke()
        }.onSuccess {
            return if(it.isSuccessful) {
                val aux = mutableListOf<Movie>()
                it.body()?.results?.forEach { movie ->
                    if(!movie.posterImage.isNullOrBlank())
                        aux.add(movie)
                }
                aux
            } else
                emptyList()
        }.onFailure {
            Log.e("ERROR", it.message)
        }
        return emptyList()
    }

    suspend fun seriesSafeCall(function: suspend() -> Response<SerieApiResponse>) : List<Serie>? {
        runCatching {
            function.invoke()
        }.onSuccess {
            return if(it.isSuccessful) it.body()!!.results else emptyList()
        }.onFailure {
            Log.e("ERROR", it.message)
        }
        return emptyList()
    }

    suspend fun favoritesRoomCall(function: suspend() -> List<MovieDB>) : List<MovieDB>? {
        runCatching {
            function.invoke()
        }.onSuccess {
            return it
        }
        return emptyList()
    }

    suspend fun transactionRoomCall(function: suspend() -> Unit) : Boolean? {
        runCatching {
            function.invoke()
        }.onSuccess {
            return true
        }
        return false
    }

}

package com.nramos.mimoflix.repo.actors

import android.util.Log
import com.nramos.mimoflix.model.ActorDetail
import com.nramos.mimoflix.model.actormovie.ActorMovie
import com.nramos.mimoflix.model.ActorMovies
import retrofit2.Response

open class BaseActorRepository {

    suspend fun actorSafeCall(function: suspend () -> Response<ActorDetail>): ActorDetail? {
        runCatching {
            function.invoke()
        }.onSuccess {
            return if (it.isSuccessful) {
                it.body()
            } else null
        }.onFailure {
            Log.e("ERROR", it.localizedMessage)
        }
        return null
    }

    suspend fun actorMoviesSafeCall(function: suspend () -> Response<ActorMovies>): List<ActorMovie>? {
        runCatching {
            function.invoke()
        }.onSuccess {
            return if (it.isSuccessful) {
                val aux = mutableListOf<ActorMovie>()
                it.body()?.movies?.forEach { movie ->
                    if(!movie.posterImage.isNullOrBlank())
                        aux.add(movie)
                }
                aux
            } else
                emptyList()
        }.onFailure {
            Log.e("ERROR", it.localizedMessage)
        }
        return emptyList()
    }

}
package com.nramos.mimoflix.repo.actors

import com.nramos.mimoflix.api.ApiService
import com.nramos.mimoflix.model.ActorDetail
import com.nramos.mimoflix.model.actormovie.ActorMovie

class ActorRepository(
    private val apiService: ApiService
) : BaseActorRepository() {

    suspend fun getActorDetail(id : Int) : ActorDetail? {
        return actorSafeCall {
            apiService.getActorDetail(id)
        }
    }

    suspend fun getActorMovies(id : Int) : List<ActorMovie>? {
        return actorMoviesSafeCall {
            apiService.getActorMovies(id)
        }
    }

}
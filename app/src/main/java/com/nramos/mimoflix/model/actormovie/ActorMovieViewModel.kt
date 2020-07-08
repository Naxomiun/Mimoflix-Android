package com.nramos.mimoflix.model.actormovie

import com.nramos.mimoflix.model.actormovie.ActorMovie

class ActorMovieViewModel (
    val movie : ActorMovie,
    val listener : (ActorMovie) -> Unit
) {
    fun onClick() {
        listener(movie)
    }
}
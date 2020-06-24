package com.nramos.mimoflix.model

class ActorMovieViewModel (
    val movie : ActorMovie,
    val listener : (ActorMovie) -> Unit
) {
    fun onClick() {
        listener(movie)
    }
}
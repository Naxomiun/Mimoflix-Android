package com.nramos.mimoflix.model.moviedb

class MovieDBViewModel (
    val movie : MovieDB,
    val listener : (MovieDB?) -> Unit,
    val removeListener : (MovieDB?) -> Unit
) {
    fun onClick() {
        listener(movie)
    }

    fun onRemove() {
        removeListener(movie)
    }
}
package com.nramos.mimoflix.model.recentmovie

class RecentMovieViewModel (
    val movie : RecentMovie,
    val listener : (RecentMovie?) -> Unit
) {
    fun onClick() {
        listener(movie)
    }
}
package com.nramos.mimoflix.model.movie

import com.nramos.mimoflix.model.PopularPromoMovie

class PopularPromoMovieViewModel (
    val movie : PopularPromoMovie,
    val listener : (PopularPromoMovie?) -> Unit
) {
    fun onClick() {
        listener(movie)
    }
}
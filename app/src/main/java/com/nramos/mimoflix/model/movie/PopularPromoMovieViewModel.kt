package com.nramos.mimoflix.model.movie

import android.view.View
import com.nramos.mimoflix.model.PopularPromoMovie

class PopularPromoMovieViewModel (
    val movie : PopularPromoMovie,
    val listener : (PopularPromoMovie?, View?) -> Unit
) {
    fun onClick(view: View) {
        listener(movie, view)
    }
}
package com.nramos.mimoflix.model.movie

import android.view.View

class RoundedPosterViewModel (
    val movie : Movie,
    val listener : (Movie, View) -> Unit
) {
    fun onClick(view: View) {
        listener(movie, view)
    }
}
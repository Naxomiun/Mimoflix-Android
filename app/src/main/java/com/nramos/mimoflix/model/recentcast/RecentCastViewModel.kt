package com.nramos.mimoflix.model.recentcast

import com.nramos.mimoflix.model.recentmovie.RecentMovie

class RecentCastViewModel (
    val cast : RecentCast,
    val listener : (RecentCast?) -> Unit
) {
    fun onClick() {
        listener(cast)
    }
}
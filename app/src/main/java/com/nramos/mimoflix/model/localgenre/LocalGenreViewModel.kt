package com.nramos.mimoflix.model.localgenre

class LocalGenreViewModel (
    val localGenre : LocalGenre,
    val listener : (LocalGenre?) -> Unit
) {
    fun onClick() {
        listener(localGenre)
    }
}
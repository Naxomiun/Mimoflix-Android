package com.nramos.mimoflix.ui.favoriteactivity

import androidx.lifecycle.ViewModel
import com.nramos.mimoflix.repo.movies.MovieRepository

class FavoriteActivityViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {

}
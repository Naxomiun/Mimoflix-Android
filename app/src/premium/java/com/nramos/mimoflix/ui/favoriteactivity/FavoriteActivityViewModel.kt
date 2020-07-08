package com.nramos.mimoflix.ui.favoriteactivity

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nramos.mimoflix.binding.RecyclerDataBindingItem
import com.nramos.mimoflix.extension.toBindingItem
import com.nramos.mimoflix.model.movie.Movie
import com.nramos.mimoflix.model.movie.RoundedPosterViewModel
import com.nramos.mimoflix.model.moviedb.MovieDB
import com.nramos.mimoflix.model.moviedb.MovieDBViewModel
import com.nramos.mimoflix.repo.movies.MovieRepository
import com.nramos.mimoflix.utils.SingleEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoriteActivityViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _movies = MutableLiveData<List<RecyclerDataBindingItem?>>()
    val movies : LiveData<List<RecyclerDataBindingItem?>> get() = _movies

    private val _movieActionEvent = MutableLiveData<SingleEvent<Int>>()
    val movieActionEvent : LiveData<SingleEvent<Int>> get() = _movieActionEvent

    init {
        getFavoriteMovies()
    }

    private fun getFavoriteMovies() {
        viewModelScope.launch {
            _movies.value = withContext(Dispatchers.IO) {
                movieRepository.getAllFavorites()?.map {
                    MovieDBViewModel(it,
                        { movie-> _movieActionEvent.value = SingleEvent(movie?.id ?: 0)},
                        { removeMovie ->
                            removeMovie?.let {
                                deleteFavorite(movie = removeMovie)
                            }
                        }).toBindingItem()
                }
            }
        }
    }

    private fun deleteFavorite(movie : MovieDB) {
        viewModelScope.launch {
            movieRepository.deleteFavorite(movie)
            getFavoriteMovies()
        }
    }

}
package com.nramos.mimoflix.ui.tabmovies

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nramos.mimoflix.binding.RecyclerDataBindingItem
import com.nramos.mimoflix.extension.toBindingItem
import com.nramos.mimoflix.model.movie.Movie
import com.nramos.mimoflix.model.movie.RoundedPosterViewModel
import com.nramos.mimoflix.repo.movies.MovieRepository
import com.nramos.mimoflix.utils.SingleEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoviesFragmentViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _popularPromoMovies = MutableLiveData<List<RecyclerDataBindingItem>>()
    val popularPromoMovies: LiveData<List<RecyclerDataBindingItem>> get() = _popularPromoMovies

    private val _popularMovies = MutableLiveData<List<RecyclerDataBindingItem>>()
    val popularMovies: LiveData<List<RecyclerDataBindingItem>> get() = _popularMovies

    private val _popularMoviesLoaded = MutableLiveData<Boolean>(false)
    val popularMoviesLoaded: LiveData<Boolean> get() = _popularMoviesLoaded

    private val _genreMovies = MutableLiveData<List<RecyclerDataBindingItem>>()
    val genreMovies: LiveData<List<RecyclerDataBindingItem>> get() = _genreMovies

    private val _movieActionEvent = MutableLiveData<SingleEvent<Pair<Movie,View>>>()
    val movieActionEvent : LiveData<SingleEvent<Pair<Movie,View>>> get() = _movieActionEvent

    init {
        getPopularMovies()
    }

    private fun getPopularMovies() {
        viewModelScope.launch {
            _popularMovies.value = withContext(Dispatchers.IO) {
                movieRepository.getPopularMovies().map {
                    RoundedPosterViewModel(it) { movie, view ->
                        _movieActionEvent.value = SingleEvent(Pair(movie, view))
                    }.toBindingItem()
                }
            }

            _popularMoviesLoaded.value = true
        }
    }

}
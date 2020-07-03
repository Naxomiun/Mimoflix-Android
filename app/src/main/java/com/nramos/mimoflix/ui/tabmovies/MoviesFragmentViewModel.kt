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



    private val _popularMovies = MutableLiveData<List<RecyclerDataBindingItem>>()
    val popularMovies: LiveData<List<RecyclerDataBindingItem>> get() = _popularMovies

    private val _popularMoviesLoaded = MutableLiveData<Boolean>(false)
    val popularMoviesLoaded: LiveData<Boolean> get() = _popularMoviesLoaded

    private val _nowplayingMovies = MutableLiveData<List<RecyclerDataBindingItem>>()
    val nowplayingMovies: LiveData<List<RecyclerDataBindingItem>> get() = _nowplayingMovies

    private val _upcomingMovies = MutableLiveData<List<RecyclerDataBindingItem>>()
    val upcomingMovies: LiveData<List<RecyclerDataBindingItem>> get() = _upcomingMovies

    private val _topratedMovies = MutableLiveData<List<RecyclerDataBindingItem>>()
    val topratedMovies: LiveData<List<RecyclerDataBindingItem>> get() = _topratedMovies

    private val _movieActionEvent = MutableLiveData<SingleEvent<Pair<Movie,View>>>()
    val movieActionEvent : LiveData<SingleEvent<Pair<Movie,View>>> get() = _movieActionEvent

    init {
        getPopularMovies()
        getNowPlayingMovies()
        getTopRatedMovies()
        getUpcomingMovies()
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

    private fun getNowPlayingMovies() {
        viewModelScope.launch {
            _nowplayingMovies.value = withContext(Dispatchers.IO) {
                movieRepository.getNowPlayingMovies().map {
                    RoundedPosterViewModel(it) { movie, view ->
                        _movieActionEvent.value = SingleEvent(Pair(movie, view))
                    }.toBindingItem()
                }
            }
        }
    }

    private fun getTopRatedMovies() {
        viewModelScope.launch {
            _topratedMovies.value = withContext(Dispatchers.IO) {
                movieRepository.getTopRatedMovies().map {
                    RoundedPosterViewModel(it) { movie, view ->
                        _movieActionEvent.value = SingleEvent(Pair(movie, view))
                    }.toBindingItem()
                }
            }
        }
    }

    private fun getUpcomingMovies() {
        viewModelScope.launch {
            _upcomingMovies.value = withContext(Dispatchers.IO) {
                movieRepository.getUpcomingMovies().map {
                    RoundedPosterViewModel(it) { movie, view ->
                        _movieActionEvent.value = SingleEvent(Pair(movie, view))
                    }.toBindingItem()
                }
            }
        }
    }

}
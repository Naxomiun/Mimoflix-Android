package com.nramos.mimoflix.ui.tabmovies

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nramos.mimoflix.binding.RecyclerDataBindingItem
import com.nramos.mimoflix.extension.toBindingItem
import com.nramos.mimoflix.model.Genre
import com.nramos.mimoflix.model.movie.Movie
import com.nramos.mimoflix.model.movie.PopularMovieViewModel
import com.nramos.mimoflix.model.movie.PopularPromoMovieViewModel
import com.nramos.mimoflix.model.movie.RecommendedMovieViewModel
import com.nramos.mimoflix.repo.MoviesRepository
import com.nramos.mimoflix.utils.SingleEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoviesFragmentViewModel(
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    private val _popularPromoMovies = MutableLiveData<List<RecyclerDataBindingItem>>()
    val popularPromoMovies: LiveData<List<RecyclerDataBindingItem>> get() = _popularPromoMovies

    private val _popularMovies = MutableLiveData<List<RecyclerDataBindingItem>>()
    val popularMovies: LiveData<List<RecyclerDataBindingItem>> get() = _popularMovies

    private val _popularMoviesLoaded = MutableLiveData<Boolean>(false)
    val popularMoviesLoaded: LiveData<Boolean> get() = _popularMoviesLoaded

    private val _genres = MutableLiveData<List<Genre>>()
    val genres : LiveData<List<Genre>> get() = _genres

    private val _genreMovies = MutableLiveData<List<RecyclerDataBindingItem>>()
    val genreMovies: LiveData<List<RecyclerDataBindingItem>> get() = _genreMovies

    private val _movieActionEvent = MutableLiveData<SingleEvent<Pair<Movie,View>>>()
    val movieActionEvent : LiveData<SingleEvent<Pair<Movie,View>>> get() = _movieActionEvent


    init {
        getPopularMovies()
        getRecommendedMovies()
    }

    private fun getPopularMovies() {
        viewModelScope.launch {
            _popularPromoMovies.value = withContext(Dispatchers.IO) {
                moviesRepository.test().map {
                    PopularPromoMovieViewModel(it) { movie, view ->

                    }.toBindingItem()
                }
            }

            _popularMovies.value = withContext(Dispatchers.IO) {
                moviesRepository.getPopularMovies().map {
                    PopularMovieViewModel(it) { movie, view ->
                        _movieActionEvent.value = SingleEvent(Pair(movie, view))
                    }.toBindingItem()
                }
            }

            _popularMoviesLoaded.value = true
        }
    }

    private fun getRecommendedMovies() {
        viewModelScope.launch {
            _genreMovies.value = withContext(Dispatchers.IO) {
                moviesRepository.getMoviesPerGenre(28).map { movie ->
                    RecommendedMovieViewModel(movie){ movie, view->

                    }.toBindingItem()
                }
            }
        }
    }


}
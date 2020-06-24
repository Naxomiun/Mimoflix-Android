package com.nramos.mimoflix.ui.moviedetail

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nramos.mimoflix.binding.RecyclerDataBindingItem
import com.nramos.mimoflix.extension.mapToFavorite
import com.nramos.mimoflix.extension.toBindingItem
import com.nramos.mimoflix.model.*
import com.nramos.mimoflix.model.movie.RelatedMovieViewModel
import com.nramos.mimoflix.repo.movies.MovieRepository
import com.nramos.mimoflix.utils.SingleEvent
import com.nramos.mimoflix.utils.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieDetailActivityViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {

    var movieId : Int = 0

    private val _movie = MutableLiveData<MovieDetail?>()
    val movie : LiveData<MovieDetail?> get() = _movie

    private val _trailer = MutableLiveData<Trailer>()
    val trailer : LiveData<Trailer> get() = _trailer

    private val _bookmarked = MutableLiveData<Boolean>()
    val bookmarked : LiveData<Boolean> get() = _bookmarked

    private val _relatedMovies = MutableLiveData<List<RecyclerDataBindingItem>>()
    val relatedMovies : LiveData<List<RecyclerDataBindingItem>> get() = _relatedMovies

    private val _actors = MutableLiveData<List<RecyclerDataBindingItem>>()
    val actors : LiveData<List<RecyclerDataBindingItem>> get() = _actors

    val backAction: SingleLiveEvent<Boolean> = SingleLiveEvent()

    private val _actorDetailAction = MutableLiveData<SingleEvent<Pair<Int, View>>>()
    val actorDetailAction : LiveData<SingleEvent<Pair<Int, View>>> get() = _actorDetailAction

    private val _trailerAction = MutableLiveData<SingleEvent<Trailer>>()
    val trailerAction : LiveData<SingleEvent<Trailer>> get() = _trailerAction

    private val _bookmarkAction = MutableLiveData<SingleEvent<Boolean>>()
    val bookmarkAction : LiveData<SingleEvent<Boolean>> get() = _bookmarkAction

    fun getMovieDetail() {
        viewModelScope.launch {
            _movie.value = withContext(Dispatchers.IO) {
                movieRepository.getMovieDetail(movieId)
            }

            _bookmarked.value = withContext(Dispatchers.IO) {
                movieRepository.checkForId(movieId) ?: false
            }

            _actors.value = withContext(Dispatchers.IO) {
                _movie.value?.credits?.cast?.map {
                    ActorViewModel(it) { actor, view ->
                        _actorDetailAction.value = SingleEvent(Pair(actor?.id ?: 0, view))
                    }.toBindingItem()
                }
            }

            _trailer.value = withContext(Dispatchers.IO) {
                movieRepository.getTrailer(movieId)
            }
        }
    }

    fun getRelatedMovies() {
        viewModelScope.launch {
            _relatedMovies.value = withContext(Dispatchers.IO) {
                movieRepository.getRelatedMovies(movieId)?.map {
                    RelatedMovieViewModel(it) { movie, view ->

                    }.toBindingItem()
                }
            }
        }
    }

    fun onBackEvent() {
        backAction.value = true
    }

    fun onPlayTrailerEvent() {
        _trailer.value?.let {
            _trailerAction.value = SingleEvent(it)
        }
    }

    fun onBookmarkEvent() {
        viewModelScope.launch {
            _bookmarked.value = withContext(Dispatchers.IO) {
                val favoriteExists = movieRepository.checkForId(movieId) ?: false
               _movie.value?.let {
                   val movieDB = it.mapToFavorite()
                   if(favoriteExists) {
                       movieRepository.deleteFavorite(movieDB)
                   } else {
                       movieRepository.saveFavorite(movieDB)
                   }
               }
               !favoriteExists
            }

            _bookmarkAction.value = withContext(Dispatchers.IO) {
                SingleEvent(movieRepository.checkForId(movieId) ?: false)
            }
        }
    }

}
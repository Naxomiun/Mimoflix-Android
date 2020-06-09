package com.nramos.mimoflix.ui.moviedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nramos.mimoflix.binding.RecyclerDataBindingItem
import com.nramos.mimoflix.extension.toBindingItem
import com.nramos.mimoflix.model.*
import com.nramos.mimoflix.model.movie.RelatedMovieViewModel
import com.nramos.mimoflix.repo.MoviesRepository
import com.nramos.mimoflix.utils.SingleEvent
import com.nramos.mimoflix.utils.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MovieDetailActivityViewModel(

    private val moviesRepository: MoviesRepository
) : ViewModel() {

    var movieId : Int = 0

    private val _movie = MutableLiveData<MovieDetail?>()
    val movie : LiveData<MovieDetail?> get() = _movie

    private val _trailer = MutableLiveData<Trailer>()
    val trailer : LiveData<Trailer> get() = _trailer

    private val _relatedMovies = MutableLiveData<List<RecyclerDataBindingItem>>()
    val relatedMovies : LiveData<List<RecyclerDataBindingItem>> get() = _relatedMovies

    private val _actors = MutableLiveData<List<RecyclerDataBindingItem>>()
    val actors : LiveData<List<RecyclerDataBindingItem>> get() = _actors

    private val _title = MutableLiveData<String>()
    val title : LiveData<String> get() = _title

    val actorDetailAction: SingleLiveEvent<Actor> = SingleLiveEvent()
    val backAction: SingleLiveEvent<Boolean> = SingleLiveEvent()

    private val _trailerAction = MutableLiveData<SingleEvent<Trailer>>()
    val trailerAction : LiveData<SingleEvent<Trailer>> get() = _trailerAction

    private val _bookmarkAction = MutableLiveData<SingleEvent<Boolean>>()
    val bookmarkAction : LiveData<SingleEvent<Boolean>> get() = _bookmarkAction

    fun getMovieDetail() {
        viewModelScope.launch {
            _movie.value = withContext(Dispatchers.IO) {
                moviesRepository.getMovieDetail(movieId)
            }

            _actors.value = withContext(Dispatchers.IO) {
                _movie.value?.credits?.cast?.map {
                    ActorViewModel(it) { actor ->
                        actorDetailAction.value = actor
                    }.toBindingItem()
                }
            }

            _trailer.value = withContext(Dispatchers.IO) {
                moviesRepository.getTrailer(movieId)
            }
        }
    }

    fun getRelatedMovies() {
        viewModelScope.launch {
            _relatedMovies.value = withContext(Dispatchers.IO) {
                moviesRepository.getRelatedMovies(movieId)?.map {
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

    fun bookmarkMovie() {

    }

}
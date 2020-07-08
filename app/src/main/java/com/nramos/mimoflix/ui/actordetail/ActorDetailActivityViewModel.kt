package com.nramos.mimoflix.ui.actordetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nramos.mimoflix.binding.RecyclerDataBindingItem
import com.nramos.mimoflix.extension.mapToRecent
import com.nramos.mimoflix.extension.toBindingItem
import com.nramos.mimoflix.model.ActorDetail
import com.nramos.mimoflix.model.actormovie.ActorMovieViewModel
import com.nramos.mimoflix.persistance.RecentDao
import com.nramos.mimoflix.repo.actors.ActorRepository
import com.nramos.mimoflix.utils.SingleEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ActorDetailActivityViewModel(
    private val actorRepository: ActorRepository,
    private val recentDao: RecentDao,
    private val actorId: Int
) : ViewModel() {

    private val _actor = MutableLiveData<ActorDetail?>()
    val actor : LiveData<ActorDetail?> get() = _actor

    private val _actorMovies = MutableLiveData<List<RecyclerDataBindingItem>>()
    val actorMovies : LiveData<List<RecyclerDataBindingItem>> get() = _actorMovies

    private val _movieActionEvent = MutableLiveData<SingleEvent<Int?>>()
    val movieActionEvent : LiveData<SingleEvent<Int?>> get() = _movieActionEvent

    init {
        getActorDetail()
        getActorMovies()
    }

    private fun getActorDetail() {
        viewModelScope.launch {
            _actor.value = withContext(Dispatchers.IO) {
                actorRepository.getActorDetail(actorId)
            }
            _actor.value?.let {
                recentDao.insertRecentCast(it.mapToRecent())
            }
        }
    }

    private fun getActorMovies() {
        viewModelScope.launch {
            _actorMovies.value = withContext(Dispatchers.IO) {
                actorRepository.getActorMovies(actorId)?.map {
                    ActorMovieViewModel(it) { movie ->
                        _movieActionEvent.value = SingleEvent(movie.id)
                    }.toBindingItem()
                }
            }
        }
    }

}
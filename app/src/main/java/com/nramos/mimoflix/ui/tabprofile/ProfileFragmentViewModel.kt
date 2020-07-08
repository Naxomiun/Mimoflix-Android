package com.nramos.mimoflix.ui.tabprofile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.nramos.mimoflix.binding.RecyclerDataBindingItem
import com.nramos.mimoflix.extension.toBindingItem
import com.nramos.mimoflix.extension.toRecentBindingItem
import com.nramos.mimoflix.model.actor.Actor
import com.nramos.mimoflix.model.actor.ActorViewModel
import com.nramos.mimoflix.model.movie.Movie
import com.nramos.mimoflix.model.movie.RoundedPosterViewModel
import com.nramos.mimoflix.persistance.RecentDao
import com.nramos.mimoflix.utils.SingleEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileFragmentViewModel(
    private val recentDao: RecentDao
) : ViewModel() {

    private val _loggedIn = MutableLiveData<Boolean>()
    val loggedIn: LiveData<Boolean> get() = _loggedIn

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> get() = _name

    private val _profileImage = MutableLiveData<String>()
    val profileImage: LiveData<String> get() = _profileImage

    private val _recentElements = MutableLiveData<List<RecyclerDataBindingItem>>()
    val recentElements: LiveData<List<RecyclerDataBindingItem>> get() = _recentElements

    private val _totalMovies = MutableLiveData<Int>()
    val totalMovies: LiveData<Int> get() = _totalMovies

    private val _totalCast = MutableLiveData<Int>()
    val totalCast: LiveData<Int> get() = _totalCast

    private val _loginActionEvent = MutableLiveData<SingleEvent<Boolean>>()
    val loginActionEvent: LiveData<SingleEvent<Boolean>> get() = _loginActionEvent

    private val _settingsActionEvent = MutableLiveData<SingleEvent<Boolean>>()
    val settingsActionEvent: LiveData<SingleEvent<Boolean>> get() = _settingsActionEvent

    private val _movieActionEvent = MutableLiveData<SingleEvent<Int>>()
    val movieActionEvent: LiveData<SingleEvent<Int>> get() = _movieActionEvent

    private val _castActionEvent = MutableLiveData<SingleEvent<Int>>()
    val castActionEvent: LiveData<SingleEvent<Int>> get() = _castActionEvent

    fun setData(account : GoogleSignInAccount?) {
        viewModelScope.launch {
            account?.let {
                _loggedIn.value = true
                _profileImage.value = it.photoUrl.toString()
                _name.value = it.givenName
            }
            getRecentElements()
        }
    }

    private fun getRecentElements() {
        viewModelScope.launch {
            _totalMovies.value = withContext(Dispatchers.IO) {
                recentDao.getCountRecentMovies()
            }

            _totalCast.value = withContext(Dispatchers.IO) {
                recentDao.getCountRecentCast()
            }

            _recentElements.value = withContext(Dispatchers.IO) {
                recentDao.getRecentMovies().map {
                    val movie = Movie(it.id, 0.0, it.image, "", 0.0,"","")
                    RoundedPosterViewModel(movie) { movie, _ ->
                        _movieActionEvent.value = SingleEvent(movie.id ?: 0)
                    }.toBindingItem()
                } + recentDao.getRecentCast().map {
                    val cast =
                        Actor(it.id, it.image)
                    ActorViewModel(cast) { cast, _ ->
                        _castActionEvent.value = SingleEvent(cast?.id ?: 0)
                    }.toRecentBindingItem()
                }
            }
        }
    }

    fun settingsEvent() {
        viewModelScope.launch {
            _settingsActionEvent.value = SingleEvent(true)
        }
    }

    fun loginEvent() {
        viewModelScope.launch {
            _loginActionEvent.value = SingleEvent(true)
        }
    }

    fun logOut() {
        viewModelScope.launch {
            _loggedIn.value = false
        }
    }

}


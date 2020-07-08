package com.nramos.mimoflix.model.actor

import android.view.View
import com.nramos.mimoflix.model.actor.Actor

class ActorViewModel(
    val actor : Actor,
    val listener : (Actor?, View) -> Unit) {

    fun onClick(view : View) {
        listener(actor, view)
    }

}
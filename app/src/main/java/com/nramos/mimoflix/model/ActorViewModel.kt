package com.nramos.mimoflix.model

import android.view.View

class ActorViewModel(
    val actor : Actor,
    val listener : (Actor?, View) -> Unit) {

    fun onClick(view : View) {
        listener(actor, view)
    }

}
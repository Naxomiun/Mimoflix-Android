package com.nramos.mimoflix.model


import com.nramos.mimoflix.binding.RecyclerItemComparator

class ActorViewModel(val actor : Actor, val listener : (Actor?) -> Unit) : RecyclerItemComparator {

    fun onClick() {
        listener(actor)
    }

    override fun isSameItem(other: Any): Boolean {
        if (this === other) return true
        if (javaClass != other.javaClass) return false

        other as ActorViewModel
        return this.actor.id == other.actor.id
    }

    override fun isSameContent(other: Any): Boolean {
        other as ActorViewModel
        return this.actor == other.actor
    }

}
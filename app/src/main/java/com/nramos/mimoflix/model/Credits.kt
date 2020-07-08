package com.nramos.mimoflix.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.nramos.mimoflix.model.actor.Actor

@Keep
data class Credits(
    @SerializedName("cast") var cast : List<Actor>?
)
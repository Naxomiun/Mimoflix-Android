package com.nramos.mimoflix.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ActorMovies(
    @SerializedName("cast") val movies : List<ActorMovie>?
)
package com.nramos.mimoflix.model

import com.google.gson.annotations.SerializedName

data class ActorMovies(
    @SerializedName("cast") val movies : List<ActorMovie>?
)
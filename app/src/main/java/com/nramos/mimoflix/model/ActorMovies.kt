package com.nramos.mimoflix.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.nramos.mimoflix.model.actormovie.ActorMovie

@Keep
data class ActorMovies(
    @SerializedName("cast") val movies : List<ActorMovie>?
)
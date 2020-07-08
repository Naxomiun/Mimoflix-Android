package com.nramos.mimoflix.model.actormovie

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ActorMovie(
    @SerializedName("id") val id : Int?,
    @SerializedName("poster_path") val posterImage : String?
)
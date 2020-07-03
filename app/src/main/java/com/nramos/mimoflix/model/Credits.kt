package com.nramos.mimoflix.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Credits(
    @SerializedName("cast") var cast : List<Actor>?
)
package com.nramos.mimoflix.model

import com.google.gson.annotations.SerializedName

data class Credits(
    @SerializedName("cast") var cast : List<Actor>?
)
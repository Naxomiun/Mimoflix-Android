package com.nramos.mimoflix.model

import com.google.gson.annotations.SerializedName

data class Trailer(
    @SerializedName("key")  val trailerId : String
)
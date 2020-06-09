package com.nramos.mimoflix.model

import com.google.gson.annotations.SerializedName

data class Actor(
    @SerializedName("id") val id : Int?,
    @SerializedName("profile_path") val profileImage : String?
)
package com.nramos.mimoflix.model.actor

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Actor(
    @SerializedName("id") val id : Int?,
    @SerializedName("profile_path") val profileImage : String?
)
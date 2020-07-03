package com.nramos.mimoflix.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ActorDetail(
    @SerializedName("id") val id : Int?,
    @SerializedName("name") val name : String?,
    @SerializedName("biography") val biography : String?,
    @SerializedName("place_of_birth") val placeOfBirth : String?,
    @SerializedName("profile_path") val profilePic : String?,
    @SerializedName("popularity") val popularity : Double?
)
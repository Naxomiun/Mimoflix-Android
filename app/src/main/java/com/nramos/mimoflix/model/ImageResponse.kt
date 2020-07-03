package com.nramos.mimoflix.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ImageResponse(
    @SerializedName("backdrops") val images : List<Backdrop>
)
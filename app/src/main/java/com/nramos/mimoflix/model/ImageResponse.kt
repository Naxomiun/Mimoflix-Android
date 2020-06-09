package com.nramos.mimoflix.model

import com.google.gson.annotations.SerializedName

data class ImageResponse(
    @SerializedName("backdrops") val images : List<Backdrop>
)
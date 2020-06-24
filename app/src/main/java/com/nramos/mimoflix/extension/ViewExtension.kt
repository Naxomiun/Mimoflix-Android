package com.nramos.mimoflix.extension

import android.util.Log
import android.view.View
import android.widget.ImageView
import coil.api.load
import coil.transform.RoundedCornersTransformation
import com.nramos.mimoflix.R
import com.nramos.mimoflix.utils.Constants.Companion.API_IMAGE_BASE_URL

fun ImageView.loadAsyncImage(url : Any?, cornerRadius : Int = R.dimen.zero)  {
    var image = url
    if(image is String) {
        image = String.format("${API_IMAGE_BASE_URL}%s", url)
        this.load(image) {
            crossfade(true)
            transformations(RoundedCornersTransformation(resources.getDimension(cornerRadius)))
        }
    }
}

fun ImageView.loadAsyncImageResource(res : Int, cornerRadius : Int = R.dimen.zero)  {
    this.load(res) {
        crossfade(true)
        transformations(RoundedCornersTransformation(resources.getDimension(cornerRadius)))
    }
}

fun ImageView.loadAsyncImage(
    url : Any?,
    topLeftCornerRadius : Int = R.dimen.zero,
    topRightCornerRadius : Int = R.dimen.zero,
    bottomLeftCornerRadius : Int = R.dimen.zero,
    bottomRightCornerRadius : Int = R.dimen.zero)  {
    var image = url
    if(image is String) {
        image = String.format("${API_IMAGE_BASE_URL}%s", url)
        this.load(image) {
            crossfade(true)
            transformations(
                RoundedCornersTransformation(
                    this@loadAsyncImage.resources.getDimension(topLeftCornerRadius),
                    this@loadAsyncImage.resources.getDimension(topRightCornerRadius),
                    this@loadAsyncImage.resources.getDimension(bottomLeftCornerRadius),
                    this@loadAsyncImage.resources.getDimension(bottomRightCornerRadius)
                )
            )
        }
    }
    else if(image is Int) {
        this.load(image) {
            crossfade(true)
            transformations(
                RoundedCornersTransformation(
                    this@loadAsyncImage.resources.getDimension(topLeftCornerRadius),
                    this@loadAsyncImage.resources.getDimension(topRightCornerRadius),
                    this@loadAsyncImage.resources.getDimension(bottomLeftCornerRadius),
                    this@loadAsyncImage.resources.getDimension(bottomRightCornerRadius)
                )
            )
        }
    }
}

fun View.show() {
    if(this.visibility == View.GONE) {
        alpha = 0f
        visibility = View.VISIBLE
        animate().alpha(1f).setDuration(700).setListener(null)
    }
}

fun View.hide() {
    if(this.visibility == View.VISIBLE) animate().alpha(0f).setDuration(700).setListener(null)
}
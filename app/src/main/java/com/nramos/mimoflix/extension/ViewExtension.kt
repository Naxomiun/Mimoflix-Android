package com.nramos.mimoflix.extension

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import coil.api.load
import coil.transform.RoundedCornersTransformation
import com.google.android.material.button.MaterialButton
import com.nramos.mimoflix.R
import com.nramos.mimoflix.utils.Constants.Companion.API_IMAGE_BASE_URL

fun MaterialButton.setCategorySelected(selected : Boolean = false) {
    val bgColor = if (selected) ContextCompat.getColor(this.context, R.color.colorAccent) else ContextCompat.getColor(this.context, R.color.lightBackgroundColor)
    val textColor = if (selected) ContextCompat.getColor(this.context, R.color.white) else ContextCompat.getColor(this.context, R.color.black)
    this.setBackgroundColor(bgColor)
    this.elevation = if(selected) 70f else 0f
    this.setTextColor(textColor)
}

fun ImageView.loadAsyncImage(url : Any?, cornerRadius : Int = R.dimen.zero)  {
    var image = url
    if(image is String) {
        image = String.format("${API_IMAGE_BASE_URL}%s", url)
        this.load(image) {
            crossfade(true)
            transformations(RoundedCornersTransformation(this@loadAsyncImage.resources.getDimension(cornerRadius)))
        }
    }
    else if(image is Int) {
        this.load(image) {
            crossfade(true)
            transformations(RoundedCornersTransformation(this@loadAsyncImage.resources.getDimension(cornerRadius)))
        }
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
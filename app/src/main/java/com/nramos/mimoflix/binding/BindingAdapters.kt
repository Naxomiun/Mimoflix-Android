package com.nramos.mimoflix.binding

import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import coil.api.load
import coil.transform.BlurTransformation
import coil.transform.RoundedCornersTransformation
import com.google.android.material.button.MaterialButton
import com.nramos.mimoflix.utils.Constants
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

@BindingAdapter("items")
fun setRecyclerBindingItems(recyclerView: RecyclerView, items: List<RecyclerDataBindingItem>?) {
    var adapter = (recyclerView.adapter as? RecyclerDataBindingAdapter)
    if (adapter == null) {
        adapter = RecyclerDataBindingAdapter()
        recyclerView.adapter = adapter
    }
    adapter.updateData(items.orEmpty())
    recyclerView.scheduleLayoutAnimation()
}

@BindingAdapter("items", "dotsIndicator")
fun setViewpagerBindingItems(viewpager: ViewPager2, items: List<RecyclerDataBindingItem>?, dotsIndicator: DotsIndicator) {
    Log.e("VIEWPAGER", "BIND")
    var adapter = (viewpager.adapter as? RecyclerDataBindingAdapter)
    if (adapter == null) {
        viewpager.offscreenPageLimit = 5
        adapter = RecyclerDataBindingAdapter()
        viewpager.adapter = adapter
        dotsIndicator.setViewPager2(viewpager)
    }

    adapter.updateData(items.orEmpty())

}

@BindingAdapter("binding:urlSrc", "binding:cornerRadius", "binding:blurRadius")
fun setImageFromUrlBinding(imageView: ImageView, url : String?, radius : Float, blur : Float) {
    if(url.isNullOrEmpty()) return
    val image: String = String.format("${Constants.API_IMAGE_BASE_URL}%s", url)
    imageView.load(image) {
        crossfade(true)
        transformations(RoundedCornersTransformation(radius), BlurTransformation(imageView.context, blur))
    }
}

@BindingAdapter("binding:urlSrc", "binding:cornerRadius")
fun setImageFromUrlBinding(imageView: ImageView, url : String?, radius : Float) {
    if(url.isNullOrEmpty()) return
    val image: String = String.format("${Constants.API_IMAGE_BASE_URL}%s", url)
    imageView.load(image) {
        crossfade(true)
        transformations(RoundedCornersTransformation(radius))
    }
}

@BindingAdapter("drawableSrc", "cornerRadius")
fun setImageFromDrawableBinding(imageView: ImageView, res : Drawable, dimen : Float) {
    imageView.load(res) {
        crossfade(true)
        transformations(RoundedCornersTransformation(dimen))
    }
}
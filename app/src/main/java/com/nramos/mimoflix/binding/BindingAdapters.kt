package com.nramos.mimoflix.binding

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import coil.api.load
import coil.transform.BlurTransformation
import coil.transform.RoundedCornersTransformation
import com.nramos.mimoflix.utils.Constants

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

@BindingAdapter("onEditorEnterAction")
fun EditText.onEditorEnterAction(function: Function1<String, Unit>?) {
    if (function == null) setOnEditorActionListener(null)
    else setOnEditorActionListener {editText, _, _ ->
        function(editText.editableText.toString())
        false
    }
}
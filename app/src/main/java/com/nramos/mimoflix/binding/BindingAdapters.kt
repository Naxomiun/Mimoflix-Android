package com.nramos.mimoflix.binding

import android.graphics.Color
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.text.buildSpannedString
import androidx.core.text.color
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.BlurTransformation
import coil.transform.RoundedCornersTransformation
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.nramos.mimoflix.R
import com.nramos.mimoflix.customviews.CarouselMovieItem
import com.nramos.mimoflix.extension.loadAsyncImageResource
import com.nramos.mimoflix.model.PopularPromoMovie
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

@BindingAdapter("items", "bindingLayoutManager")
fun setRecyclerBindingItems(recyclerView: RecyclerView, items: List<RecyclerDataBindingItem>?, searching : Boolean) {
    var adapter = (recyclerView.adapter as? RecyclerDataBindingAdapter)
    if (adapter == null) {
        adapter = RecyclerDataBindingAdapter()
        recyclerView.adapter = adapter
    }
    if(searching) {
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
    } else {
        recyclerView.layoutManager = GridLayoutManager(recyclerView.context, 3)
    }
    adapter.updateData(items.orEmpty())
    recyclerView.scheduleLayoutAnimation()
}

@BindingAdapter("carouselItems")
fun setCarouselBindingItems(recyclerView: RecyclerView, items: List<RecyclerDataBindingItem>?) {
    var adapter = (recyclerView.adapter as? RecyclerDataBindingAdapter)
    if (adapter == null) {
        adapter = RecyclerDataBindingAdapter()
        LinearSnapHelper().attachToRecyclerView(recyclerView)
        recyclerView.adapter = adapter
    }
    adapter.updateData(items.orEmpty())
    recyclerView.scheduleLayoutAnimation()
}

@BindingAdapter("flexboxItems")
fun setFlexboxBindingItems(recyclerView: RecyclerView, items: List<RecyclerDataBindingItem>?) {
    val layoutManager = FlexboxLayoutManager(recyclerView.context)
    layoutManager.flexDirection = FlexDirection.ROW
    layoutManager.justifyContent = JustifyContent.SPACE_AROUND
    layoutManager.flexWrap = FlexWrap.WRAP
    recyclerView.layoutManager = layoutManager
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
fun setImageFromDrawableBinding(imageView: ImageView, res : Int, dimen : Float) {
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

@BindingAdapter("customToolbarTitle")
fun setCustomToolbarTitle(textView : TextView, title : String) {
    val context = textView.context
    val spannedString = buildSpannedString {
        color(ContextCompat.getColor(context, R.color.colorAccent)) {
            append(context.getString(R.string.app_name_first))
        }
        color(Color.BLACK){
            append(context.getString(R.string.app_name_second))
        }
    }
    textView.text = spannedString
}

@BindingAdapter("customCardBackgroundColor")
fun setCustomToolbarTitle(cv : CardView, color : Int) {
    cv.setCardBackgroundColor(ContextCompat.getColor(cv.context, color))
}

@BindingAdapter("carouselItem")
fun setCustomToolbarTitle(carouselMovieItem: CarouselMovieItem, popularPromoMovie: PopularPromoMovie) {
    carouselMovieItem.cimage.loadAsyncImageResource(popularPromoMovie.posterImage ?: 0)
    carouselMovieItem.ctitle.text = popularPromoMovie.title
    carouselMovieItem.crating.rating = popularPromoMovie.calculatePopularity()
}

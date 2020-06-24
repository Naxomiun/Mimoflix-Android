package com.nramos.mimoflix.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.cardview.widget.CardView
import com.nramos.mimoflix.R
import com.nramos.mimoflix.databinding.CustomCarouselItemBinding
import com.nramos.mimoflix.extension.loadAsyncImageResource

class CarouselMovieItem @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

    private val binding = CustomCarouselItemBinding.inflate(LayoutInflater.from(context), this, true)

    val ctitle get() = binding.tvTitle
    val crating get() = binding.rbMovie
    val cimage get() = binding.ivPosterMovie

    init {
        this.radius = 40f
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.CarouselMovieItem, 0, 0)
            val image = typedArray.getInt(R.styleable.CarouselMovieItem_image, 0)
            val title = typedArray.getString(R.styleable.CarouselMovieItem_title)
            val rating = typedArray.getFloat(R.styleable.CarouselMovieItem_rating, 0f)
            typedArray.recycle()

            binding.tvTitle.text = title
            binding.rbMovie.rating = rating
            binding.ivPosterMovie.loadAsyncImageResource(image)
        }
    }
}
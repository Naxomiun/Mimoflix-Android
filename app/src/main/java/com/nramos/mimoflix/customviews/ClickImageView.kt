package com.nramos.mimoflix.customviews

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.view.animation.CycleInterpolator
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.ViewCompat
import androidx.core.view.ViewPropertyAnimatorListener
import com.nramos.mimoflix.R

class ClickImageView : AppCompatImageView {

    var scale = 0.9f
    var duration = 500

    private var onClickListener: OnClickListener? = null
    private var onFinishListener: ClickFinishListener? = null

    constructor(context: Context) : super(context) {
        onCreate()
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        onCreate()
        getAttrs(attributeSet)
    }

    constructor(context: Context, attributeSet: AttributeSet, defStyle: Int) : super(context,
        attributeSet, defStyle) {
        onCreate()
        getAttrs(attributeSet, defStyle)
    }

    private fun onCreate() {
        this.isClickable = true
        super.setOnClickListener {
            clickAnimation(this) {
                setDuration(this@ClickImageView.duration)
                setScaleX(this@ClickImageView.scale)
                setScaleY(this@ClickImageView.scale)
                setOnFinishListener { invokeListeners() }
            }.doAction()
        }
    }

    private fun getAttrs(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ClickImageView)
        try {
            setTypeArray(typedArray)
        } finally {
            typedArray.recycle()
        }
    }

    private fun getAttrs(attrs: AttributeSet, defStyle: Int) {
        val typedArray =
            context.obtainStyledAttributes(attrs, R.styleable.ClickImageView, defStyle, 0)
        try {
            setTypeArray(typedArray)
        } finally {
            typedArray.recycle()
        }
    }

    private fun setTypeArray(typedArray: TypedArray) {
        this.scale = typedArray.getFloat(R.styleable.ClickImageView_imageView_scale, scale)
        this.duration = typedArray.getInt(R.styleable.ClickImageView_imageView_duration, duration)
    }

    override fun setOnClickListener(listener: OnClickListener?) {
        this.onClickListener = listener
    }

    fun setOnFinishListener(listener: ClickFinishListener) {
        this.onFinishListener = listener
    }

    private fun invokeListeners() {
        this.onClickListener?.onClick(this)
        this.onFinishListener?.onFinished()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }
}

fun clickAnimation(view: View, block: ClickAnimation.() -> Unit): ClickAnimation = ClickAnimation(view).apply(block)

class ClickAnimation(private val view: View) {

    @JvmField
    var scaleX = 0.7f
    @JvmField
    var scaleY = 0.7f
    @JvmField
    var duration = 400
    @JvmField
    var listener: ViewPropertyAnimatorListener? = null
    @JvmField
    var finishListener: ClickFinishListener? = null
    private var isAnimating: Boolean = false

    fun setScaleX(scaleX: Float): ClickAnimation = apply { this.scaleX = scaleX }
    fun setScaleY(scaleY: Float): ClickAnimation = apply { this.scaleY = scaleY }
    fun setDuration(duration: Int): ClickAnimation = apply { this.duration = duration }

    fun setOnFinishListener(block: () -> Unit): ClickAnimation = apply {
        this.finishListener = object : ClickFinishListener {
            override fun onFinished() {
                block()
            }
        }
    }

    fun doAction() {
        if (!this.isAnimating && this.view.scaleX == 1f) {
            val animatorCompat = ViewCompat.animate(view)
                .setDuration(this.duration.toLong())
                .scaleX(this.scaleX)
                .scaleY(this.scaleY)
                .setInterpolator(CycleInterpolator(0.5f)).apply {
                    setListener(object : ViewPropertyAnimatorListener {
                        override fun onAnimationCancel(view: View?) = Unit
                        override fun onAnimationStart(view: View?) {
                            isAnimating = true
                        }

                        override fun onAnimationEnd(view: View?) {
                            isAnimating = false
                            finishListener?.onFinished()
                        }
                    })
                }
            this.listener?.let { animatorCompat.setListener(it) }
            if (this.view is ViewGroup) {
                for (index in 0 until this.view.childCount) {
                    val nextChild = this.view.getChildAt(index)
                    ViewCompat.animate(nextChild)
                        .setDuration(this.duration.toLong())
                        .scaleX(this.scaleX)
                        .scaleY(this.scaleY)
                        .setInterpolator(CycleInterpolator(0.5f))
                        .withLayer()
                        .start()
                }
            }
            animatorCompat.withLayer().start()
        }
    }
    fun isAnimating(): Boolean = this.isAnimating
}

//Click interface
interface ClickFinishListener {
    fun onFinished()
}

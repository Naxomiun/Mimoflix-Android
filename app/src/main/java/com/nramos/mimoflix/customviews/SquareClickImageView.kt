package com.nramos.mimoflix.customviews

import android.content.Context
import android.util.AttributeSet

class SquareClickImageView : ClickImageView {

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

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)
    }
}
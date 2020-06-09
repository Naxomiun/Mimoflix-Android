package com.nramos.mimoflix.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class EmptyRecyclerView : RecyclerView {

    private var mEmptyView: View? = null
    private val mObserver: AdapterDataObserver = object : AdapterDataObserver() {
        override fun onChanged() { checkIfEmpty() }
        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) { checkIfEmpty() }
        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) { checkIfEmpty() }
    }

    constructor(context: Context?) : super(context!!)
    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(context!!, attrs, defStyle)

    fun checkIfEmpty() {
        if (mEmptyView != null && adapter != null) {
            val emptyViewVisible = adapter!!.itemCount == 0
            mEmptyView!!.visibility = if (emptyViewVisible) View.VISIBLE else View.GONE
            visibility = if (emptyViewVisible) View.GONE else View.VISIBLE
        }
    }

    override fun setAdapter(adapter: Adapter<*>?) {
        val oldAdapter = getAdapter()
        oldAdapter?.unregisterAdapterDataObserver(mObserver)
        super.setAdapter(adapter)
        adapter?.registerAdapterDataObserver(mObserver)
        checkIfEmpty()
    }

    fun setEmptyView(emptyView: View?) {
        mEmptyView = emptyView
        checkIfEmpty()
    }

}
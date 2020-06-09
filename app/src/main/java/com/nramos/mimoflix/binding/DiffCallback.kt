package com.nramos.mimoflix.binding

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

class DiffCallback : DiffUtil.ItemCallback<RecyclerDataBindingItem>() {

    @SuppressLint("DiffUtilEquals")
    override fun areItemsTheSame(oldItem: RecyclerDataBindingItem, newItem: RecyclerDataBindingItem): Boolean {
        val oldData = oldItem.item
        val newData = newItem.item
        return if (oldData is RecyclerItemComparator && newData is RecyclerItemComparator) {
            oldData.isSameItem(newData)
        } else oldData == newData
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: RecyclerDataBindingItem, newItem: RecyclerDataBindingItem): Boolean {
        val oldData = oldItem.item
        val newData = newItem.item
        return if (oldData is RecyclerItemComparator && newData is RecyclerItemComparator) {
            oldData.isSameContent(newData)
        } else oldData == newData
    }
}
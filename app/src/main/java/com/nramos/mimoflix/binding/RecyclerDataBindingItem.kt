package com.nramos.mimoflix.binding

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding

data class RecyclerDataBindingItem(
    val item: Any,
    @LayoutRes val layout : Int,
    val variableId: Int
) {
    fun bind(binding: ViewDataBinding) {
        binding.setVariable(variableId, item)
    }
}
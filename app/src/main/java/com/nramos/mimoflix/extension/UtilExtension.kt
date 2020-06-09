package com.nramos.mimoflix.extension

import java.util.*


fun <T> List<T>.safeSubList(fromIndex: Int, toIndex: Int): List<T>? {
    var fromIndex = fromIndex
    var toIndex = toIndex
    val size = this.size
    if (fromIndex >= size || toIndex <= 0 || fromIndex >= toIndex) {
        return Collections.emptyList()
    }
    fromIndex = 0.coerceAtLeast(fromIndex)
    toIndex = size.coerceAtMost(toIndex)
    return this.subList(fromIndex, toIndex)
}

fun Int.minsToStringFormat() : String {
    val hours: Int = this / 60
    val minutes: Int = this % 60
   return String.format("%dh %02dm", hours, minutes)
}

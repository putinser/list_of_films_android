package com.balinasoft.themoviedb.common.extension


/** Returns `true` if the collection is null or empty. */
inline fun <T> Collection<T>?.isNullOrEmpty(): Boolean = this == null || this.isEmpty()

fun <T> MutableList<T>.set(element: T) {
    val indexOf = this.indexOf(element)
    if (indexOf >= 0) {
        this[indexOf] = element
    } else {
        this.add(element)
    }
}

fun <T> List<T>.sumOfObjects(valback: (prewSum: T, current: T) -> T): T? {
    if (isEmpty()) return null

    var sum: T = first()

    for (i in 1 until size) {
        sum = valback(sum, get(i))
    }

    return sum
}

fun <T> List<T?>.removeNulls(): List<T> {
    val t = this
    return t.filter { it != null }.map { it!! }
}
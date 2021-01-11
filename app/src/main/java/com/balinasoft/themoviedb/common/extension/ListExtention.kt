package com.balinasoft.themoviedb.common.extension


fun <KEY, OBJECT> List<OBJECT>.buildHashMap(by: (OBJECT) -> KEY): HashMap<KEY, OBJECT> {
    val result = HashMap<KEY, OBJECT>()
    forEach {
        val key = by(it)
        result[key] = it
    }
    return result
}

fun <T, P, O> List<T>.groupBy(by: (T) -> P, valueBuilder: (T) -> O): HashMap<P, List<O>> {
    val result = HashMap<P, List<O>>()
    forEach {
        val key = by(it)
        var list = result[key] as ArrayList<O>?
        if (list == null) {
            list = ArrayList<O>()
            result[key] = list
        }
        list.add(valueBuilder(it))
    }
    return result
}

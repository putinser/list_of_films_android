package com.balinasoft.themoviedb.common.view.list.adapter

import androidx.recyclerview.widget.DiffUtil


abstract class MultiLayoutAdapterSmart : MultiLayoutAdapter() {

    protected fun smartUpdate(newList: List<Any>) {
        val diffCallback = DiffUtilCallback(data, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        data.clear()
        data.addAll(newList)

        diffResult.dispatchUpdatesTo(this)
    }
}
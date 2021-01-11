package com.balinasoft.themoviedb.common.view.list.adapter

import androidx.recyclerview.widget.DiffUtil

class DiffUtilCallback(val oldList: List<Any>, val newList: List<Any>) : DiffUtil.Callback() {

    override fun areItemsTheSame(p0: Int, p1: Int): Boolean {
        val old = oldList[p0]
        val new = newList[p1]
        if (old is ISmartModel && new is ISmartModel) {
            return old.areItemsTheSame(new)
        }
        return false
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areContentsTheSame(oldItemIndex: Int, newIndex: Int): Boolean {
        val old = oldList[oldItemIndex]
        val new = newList[newIndex]

        if (old is ISmartModel && new is ISmartModel) {
            return old.isContentsTheSame(new)
        }
        return false
    }
}

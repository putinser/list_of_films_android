package com.balinasoft.themoviedb.common.view.list.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.balinasoft.themoviedb.common.extension.CallBackK

open class SimpleViewHolder<T>(view: View) : RecyclerView.ViewHolder(view) {

    var onClick: CallBackK<T>? = null

    fun removeClickListener() {
        itemView.setOnClickListener(null)
        itemView.isClickable = false
    }

    fun getItemView(): View {
        return itemView
    }

    open fun onBindViewHolder(item: T) {

    }
}
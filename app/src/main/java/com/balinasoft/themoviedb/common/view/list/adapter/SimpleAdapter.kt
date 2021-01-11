package com.balinasoft.themoviedb.common.view.list.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.balinasoft.themoviedb.common.extension.CallBackK
import com.balinasoft.themoviedb.common.extension.onClick


abstract class SimpleAdapter<T>() : RecyclerView.Adapter<SimpleViewHolder<T>>() {

    private var builder: ((ViewGroup) -> SimpleViewHolder<T>)? = null

    var onItemClicked: CallBackK<T>? = null

    constructor(builder: ((ViewGroup) -> SimpleViewHolder<T>)) : this() {
        this.builder = builder
    }

    protected val items = ArrayList<T>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleViewHolder<T> {
        return getViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(p0: SimpleViewHolder<T>, p1: Int) {
        val item = items[p1]
        p0.onBindViewHolder(item)
        onItemClicked?.let { callback ->
            p0.itemView.onClick {
                callback.invoke(item)
            }
        }
    }

    open fun getViewHolder(parent: ViewGroup): SimpleViewHolder<T> {

        val builder = this.builder ?: throw IllegalStateException("getViewHolder not implemented")

        val viewHolder = builder.invoke(parent)

        return viewHolder
    }

    open fun insertData(list: List<T>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

}

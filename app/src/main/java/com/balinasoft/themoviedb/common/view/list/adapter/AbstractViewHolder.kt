package com.balinasoft.themoviedb.common.view.list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class AbstractViewHolder<T>(parent: ViewGroup, idLayout: Int) :
    SimpleViewHolder<T>(buld(parent, idLayout)) {

    companion object {
        fun buld(parent: ViewGroup, idLayout: Int): View {
            return LayoutInflater.from(parent.context).inflate(idLayout, parent, false)
        }
    }
}
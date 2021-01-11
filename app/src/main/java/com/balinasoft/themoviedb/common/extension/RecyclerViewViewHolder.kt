package com.balinasoft.themoviedb.common.extension

import android.content.res.Resources
import androidx.recyclerview.widget.RecyclerView


inline val RecyclerView.ViewHolder.context
    get() = this.itemView.context!!

inline val RecyclerView.ViewHolder.resources: Resources
    get() = this.itemView.context!!.resources

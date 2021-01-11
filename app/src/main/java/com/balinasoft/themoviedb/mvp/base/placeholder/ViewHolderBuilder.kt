package com.balinasoft.themoviedb.mvp.base.placeholder

import android.view.ViewGroup
import com.vmeste.app.common.placeholder.ScreenViewHolder

interface ViewHolderBuilder<T : ScreenViewHolder> {
    fun provide(viewGroup: ViewGroup): T
}

class ViewHolderBuilderImpl<T : ScreenViewHolder>(val builder: (viewGroup: ViewGroup) -> T) :
    ViewHolderBuilder<T> {
    override fun provide(viewGroup: ViewGroup): T {
        return builder(viewGroup)
    }
}

package com.balinasoft.themoviedb.mvp.base.placeholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vmeste.app.common.placeholder.ScreenViewHolder

abstract class AbstractScreenViewHolder(layoutId: Int, viewGroup: ViewGroup) :
    ScreenViewHolder {

    protected val m_view: View

    override fun getView(): View {
        return m_view
    }

    init {
        val context = viewGroup.context
        m_view = LayoutInflater.from(context).inflate(layoutId, viewGroup, false)
    }

    override fun visibilityChanged(visible: Boolean) {

    }
}
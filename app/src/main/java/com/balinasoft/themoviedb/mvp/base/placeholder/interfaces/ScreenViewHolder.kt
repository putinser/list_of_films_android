package com.vmeste.app.common.placeholder

import android.view.View

interface ScreenViewHolder {
    fun getView(): View
    fun visibilityChanged(visible: Boolean)
}
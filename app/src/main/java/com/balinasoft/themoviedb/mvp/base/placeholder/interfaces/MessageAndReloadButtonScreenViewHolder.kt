package com.balinasoft.themoviedb.mvp.base.placeholder.interfaces

import com.balinasoft.themoviedb.common.extension.CallBackKUnit
import com.vmeste.app.common.placeholder.ScreenViewHolder

interface MessageAndReloadButtonScreenViewHolder : ScreenViewHolder {
    fun setMessage(message: String)
    fun setOnReload(callback: CallBackKUnit)
    fun setButtonText(text: String)
}
package com.vmeste.app.mvp.base.baseScreen.applicationBar

import android.view.View
import com.balinasoft.themoviedb.common.extension.CallBackKUnit

interface ApplicationBarViewHolder {
    fun setBackButtonVisibility(visible: Boolean)
    fun setText(text: String)
    fun getView(): View
    var onBackButtonClicked: CallBackKUnit
}

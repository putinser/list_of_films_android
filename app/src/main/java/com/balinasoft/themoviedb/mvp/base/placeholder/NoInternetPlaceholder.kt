package com.balinasoft.themoviedb.mvp.base.placeholder

import android.view.ViewGroup
import com.balinasoft.themoviedb.R
import com.balinasoft.themoviedb.common.extension.CallBackKUnit
import com.balinasoft.themoviedb.common.extension.onClick
import com.balinasoft.themoviedb.mvp.base.placeholder.interfaces.MessageAndReloadButtonScreenViewHolder
import kotlinx.android.synthetic.main.placeholder_error.view.*

class NoInternetPlaceholder(viewGroup: ViewGroup) :
    AbstractScreenViewHolder(R.layout.placeholder_error, viewGroup),
    MessageAndReloadButtonScreenViewHolder {

    private var reloadButtonCallback: CallBackKUnit? = null

    override fun setOnReload(callback: CallBackKUnit) {
        reloadButtonCallback = callback
    }

    override fun setMessage(message: String) {
        getView().errorMessageText.text = message
    }

    override fun setButtonText(text: String) {
        getView().reloadButton.text = text
    }

    init {
        getView().reloadButton.onClick {
            reloadButtonCallback?.invoke()
        }
    }
}
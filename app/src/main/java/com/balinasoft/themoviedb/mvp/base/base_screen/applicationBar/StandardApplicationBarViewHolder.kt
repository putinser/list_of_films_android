package com.balinasoft.themoviedb.mvp.base.base_screen.applicationBar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.balinasoft.themoviedb.common.extension.CallBackKUnit
import com.vmeste.app.mvp.base.baseScreen.applicationBar.ApplicationBarViewHolder

open class StandardApplicationBarViewHolder(layoutInflater: LayoutInflater, container: ViewGroup) :
    ApplicationBarViewHolder {

    protected val view: StandardApplicationBarView = StandardApplicationBarView(container.context)

    override var onBackButtonClicked: CallBackKUnit
        get() {
            return view.onBackButtonClicked
        }
        set(value) {
            view.onBackButtonClicked = value
        }

    init {

    }

    override fun setBackButtonVisibility(visible: Boolean) {
        view.setBackButtonVisibility(visible)
    }

    override fun setText(text: String) {
        view.setText(text)
    }

    override fun getView(): View {
        return view
    }
}

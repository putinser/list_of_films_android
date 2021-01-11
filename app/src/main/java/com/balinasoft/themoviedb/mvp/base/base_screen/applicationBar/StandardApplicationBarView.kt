package com.balinasoft.themoviedb.mvp.base.base_screen.applicationBar

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.balinasoft.themoviedb.R
import com.balinasoft.themoviedb.common.extension.CallBackKUnit
import com.balinasoft.themoviedb.common.extension.onClick
import com.balinasoft.themoviedb.common.extension.visibleOrInvisible
import com.vmeste.app.mvp.base.baseScreen.applicationBar.ApplicationBarViewHolder
import kotlinx.android.synthetic.main.view_standart_application_toolbar.view.*

class StandardApplicationBarView : LinearLayout, ApplicationBarViewHolder {

    private val NAVIGATION_BUTTON_RES = R.drawable.ic_back

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    init {
        View.inflate(context, R.layout.view_standart_application_toolbar, this)

        image_right.onClick {
            onRightImageClicked?.invoke()
        }

        back_button_bt.onClick {
            onBackButtonClicked?.invoke()
        }

        setRightImage(null)
        setBackButtonVisibility(false)
        setText("")

    }

    fun getButton(): View {
        return image_right
    }

    fun setRightImage(imageRes: Int?) {
        image_right.setImageDrawable(imageRes?.let { ContextCompat.getDrawable(context, imageRes) })
    }

    var onRightImageClicked: CallBackKUnit? = null

    override fun setBackButtonVisibility(visible: Boolean) {
        back_button_bt.visibleOrInvisible(visible)
    }

    override fun setText(text: String) {
        headerTV.text = text
    }

    override fun getView(): View {
        return this
    }

    override var onBackButtonClicked: CallBackKUnit = {}
}
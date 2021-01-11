package com.balinasoft.themoviedb.mvp.base.placeholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.balinasoft.themoviedb.R


import com.vmeste.app.common.placeholder.MessageScreenViewHolder
import kotlinx.android.synthetic.main.placeholder_message.view.*


class MessageViewHolder(val parent: ViewGroup) : MessageScreenViewHolder {

    private val view =
        LayoutInflater.from(parent.context).inflate(R.layout.placeholder_message, parent, false)

    override fun getView(): View {
        return view
    }

    override fun visibilityChanged(visible: Boolean) {

    }

    override fun setMessage(message: String) {
        view.message_tv.text = message
    }

}
package com.balinasoft.themoviedb.common.view.list.adapter

import android.view.ViewGroup
import com.balinasoft.themoviedb.R
import com.balinasoft.themoviedb.common.extension.CallBackKUnit
import com.balinasoft.themoviedb.common.extension.onClick
import kotlinx.android.synthetic.main.item_error_with_reload_button.view.*

abstract class MultiLayoutLoadingAdapter() : MultiLayoutAdapterSmart() {

    private var items = ArrayList<Any>()

    init {
        registerCell(ProgressBarItem::class.java) {
            ProgressBarViewHolder(it)
        }

        registerCell(MessageWithReloadButton::class.java) {
            MessageWithReloadButtonViewHolder(it)
        }

        updateDataAndProgress()
    }

    var onReloadButtonClicked: CallBackKUnit? = null

    private var message: MessageWithReloadButton? = null
    private var progressBar: ProgressBarItem? = null

    class ProgressBarItem() : ISmartModel {
        override fun areItemsTheSame(other: Any): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false
            return true
        }
    }

    data class MessageWithReloadButton(val message: String?) : ISmartModel {
        override fun areItemsTheSame(other: Any): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false
            return true
        }
    }

    class ProgressBarViewHolder(parent: ViewGroup) :
        AbstractViewHolder<ProgressBarItem>(parent, R.layout.item_progress_bar) {

    }

    inner class MessageWithReloadButtonViewHolder(parent: ViewGroup) :
        AbstractViewHolder<MessageWithReloadButton>(
            parent,
            R.layout.item_error_with_reload_button
        ) {
        override fun onBindViewHolder(item: MessageWithReloadButton) {
            super.onBindViewHolder(item)

            with(itemView) {

                message_tv.text = item.message
                reload_button.onClick {
                    onReloadButtonClicked?.invoke()
                }
            }
        }
    }

    fun showProgressBar(show: Boolean) {
        if (show) {
            progressBar = ProgressBarItem()
        } else {
            progressBar = null
        }
        updateDataAndProgress()
    }

    fun showErrorMessage(show: Boolean, message: String?) {
        if (show) {
            this.message = MessageWithReloadButton(message)
        } else {
            this.message = null
        }
        updateDataAndProgress()
    }

    fun setData(list: List<Any>) {
        this.items.clear()
        this.items.addAll(list)
        updateDataAndProgress()
    }

    private fun updateDataAndProgress() {
        val resultList = ArrayList<Any>()
        resultList.addAll(items)

        val message = message
        val progress = progressBar

        if (message != null) {
            resultList.add(message)
        }

        if (message == null && progress != null) {
            resultList.add(progress)
        }

        smartUpdate(resultList)
    }

}
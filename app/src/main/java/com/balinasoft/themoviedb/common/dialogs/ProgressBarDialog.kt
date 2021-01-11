package com.balinasoft.themoviedb.common.dialogs

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatDialog
import com.balinasoft.themoviedb.R
import com.balinasoft.themoviedb.common.extension.visibleOrGone
import kotlinx.android.synthetic.main.dialog_progress_bar.view.*

class ProgressBarDialog(context: Context, message: String?) : AppCompatDialog(context) {
    init {

        val view = LayoutInflater.from(context).inflate(R.layout.dialog_progress_bar, null)

        setContentView(view)

        view.progress_bar_message.text = message ?: ""
        view.progress_bar_message.visibleOrGone(message != null)

    }
}
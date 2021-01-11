package com.balinasoft.themoviedb.common.extension

import android.view.MotionEvent
import android.view.View
import android.widget.EditText


fun EditText.makeScrollable() {
    this.setOnTouchListener(object : View.OnTouchListener {
        override fun onTouch(v: View, event: MotionEvent): Boolean {
            if (this@makeScrollable.hasFocus()) {
                v.parent.requestDisallowInterceptTouchEvent(true)
                when (event.action and MotionEvent.ACTION_MASK) {
                    MotionEvent.ACTION_SCROLL -> {
                        v.parent.requestDisallowInterceptTouchEvent(false)
                        return true
                    }
                }
            }
            return false
        }
    })
}
package com.balinasoft.themoviedb.common.extension

import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.ShapeDrawable
import android.view.View
import androidx.core.content.ContextCompat


fun View.onClick(l: (v: View?) -> Unit) {
    setOnClickListener(l)
}

fun View.onLongClick(l: (v: android.view.View?) -> Boolean) {
    setOnLongClickListener(l)
}

fun View.visibleOrGone(visibility: Boolean) {
    this.visibility = if (visibility) View.VISIBLE else View.GONE
}

fun View.visibleOrInvisible(visibility: Boolean) {
    this.visibility = if (visibility) View.VISIBLE else View.INVISIBLE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.enable() {
    this.isEnabled = true
}

fun View.disable() {
    this.isEnabled = false
}

fun View.setShapeColorValue(colorVal: Int) {
    val background: Drawable = this.getBackground()
    if (background is ShapeDrawable) {
        (background as ShapeDrawable).paint.color = colorVal
    } else if (background is GradientDrawable) {
        background.setColor(colorVal)
    } else if (background is ColorDrawable) {
        (background as ColorDrawable).color = colorVal
    }
}

fun View.changeShapeColorResource(colorRes: Int) {
    setShapeColorValue(ContextCompat.getColor(context, colorRes))
}



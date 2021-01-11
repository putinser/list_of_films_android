package com.balinasoft.themoviedb.common.extension

import android.widget.ImageView
import com.balinasoft.themoviedb.common.utils.ImageLoader

fun ImageView.load(url: String?, placeholder: Int? = null) {

    ImageLoader.load(url = url, placeHolder = placeholder, imageView = this)

}
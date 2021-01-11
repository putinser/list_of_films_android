package com.balinasoft.themoviedb.common.utils

import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

object ImageLoader {

    fun load(url: String?, imageView: ImageView, placeHolder: Int? = null) {
        var uri: Uri? = null

        if (url != null) {
            uri = Uri.parse(url)
        }


        loadImageUri(uri, imageView, placeHolder)
    }

    fun loadImageUri(url: Uri?, imageView: ImageView, placeHolder: Int? = null) {


        var builder = Glide.with(imageView.context).load(url)

        builder = builder.apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.AUTOMATIC))

        if (placeHolder != null) {
            builder = builder.apply(RequestOptions().placeholder(placeHolder))
        }

        builder.into(imageView)

    }
}

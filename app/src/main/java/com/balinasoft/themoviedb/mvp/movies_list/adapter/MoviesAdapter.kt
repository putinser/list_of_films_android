package com.balinasoft.themoviedb.mvp.movies_list.adapter

import com.balinasoft.themoviedb.common.view.list.adapter.MultiLayoutLoadingAdapter
import com.balinasoft.themoviedb.mvp.movies_list.MovieItem

class MoviesAdapter() : MultiLayoutLoadingAdapter() {

    init {
        registerCell(MovieItem::class.java) {
            MovieViewHolder(it)
        }
    }

}
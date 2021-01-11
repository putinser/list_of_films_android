package com.balinasoft.themoviedb.mvp.movies_list.adapter

import android.view.ViewGroup
import com.balinasoft.themoviedb.R
import com.balinasoft.themoviedb.common.extension.load
import com.balinasoft.themoviedb.common.view.list.adapter.AbstractViewHolder
import com.balinasoft.themoviedb.mvp.movies_list.MovieItem
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieViewHolder(parent: ViewGroup) :
    AbstractViewHolder<MovieItem>(parent, R.layout.item_movie) {

    override fun onBindViewHolder(item: MovieItem) {
        super.onBindViewHolder(item)

        with(itemView) {
            poster_iv.load(item.poster, null)
            ganres_tv.text = item.genresString
            movie_title.text = item.movieTitle
            release_date.text = item.releaseDate
        }

    }
}
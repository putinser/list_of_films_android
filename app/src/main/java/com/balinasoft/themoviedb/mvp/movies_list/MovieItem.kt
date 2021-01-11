package com.balinasoft.themoviedb.mvp.movies_list

import com.balinasoft.themoviedb.common.extension.sumOfObjects
import com.balinasoft.themoviedb.common.view.list.adapter.ISmartModel

data class MovieItem(
    val id: Long,
    val movieTitle: String,
    val genres: List<String>,
    val poster: String?,
    val releaseDate: String?
) : ISmartModel {

    val genresString = genres.sumOfObjects { prewSum, current -> "$prewSum, $current" }

    override fun areItemsTheSame(other: Any): Boolean {
        if (other !is MovieItem) return false
        if (other.id != id) return false
        return true
    }
}
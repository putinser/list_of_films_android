package com.balinasoft.themoviedb.mvp.movies_list

import com.balinasoft.themoviedb.BuildConfig
import com.balinasoft.themoviedb.common.extension.buildHashMap
import com.balinasoft.themoviedb.common.extension.removeNulls
import com.balinasoft.themoviedb.common.utils.DateTimeUtils
import com.balinasoft.themoviedb.data.model.GenreDtoIn
import com.balinasoft.themoviedb.data.model.Language
import com.balinasoft.themoviedb.data.model.MovieDtoIn
import com.balinasoft.themoviedb.data.model.Region
import com.balinasoft.themoviedb.data.repository.movies_repository.IMoviesRepository
import com.balinasoft.themoviedb.di.DaggerUtils
import com.balinasoft.themoviedb.mvp.base.list.ListInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PopularListInteractor : ListInteractor<MovieItem> {

    @Inject
    lateinit var repository: IMoviesRepository

    init {
        DaggerUtils.appComponent.inject(this)
    }

    private val genres: HashMap<Int, GenreDtoIn> by lazy {
        repository.getGenres(Language.ENG).buildHashMap { it.id }
    }

    override suspend fun load(page: Int, search: String?): List<MovieItem> =
        withContext(Dispatchers.IO) {
            repository.getPopularMovies(page + 1, Language.ENG, Region.US).map {
                mapMovie(it, genres)
            }
        }

    private fun mapMovie(movieDtoIn: MovieDtoIn, genres: HashMap<Int, GenreDtoIn>): MovieItem {
        return MovieItem(
            id = movieDtoIn.id.toLong(),
            genres = movieDtoIn.genre_ids.map {
                genres[it]?.name
            }.removeNulls(),
            movieTitle = movieDtoIn.title,
            poster = BuildConfig.IMAGE_PREFIX_SMALL + movieDtoIn.poster_path,
            releaseDate = movieDtoIn.release_date?.let {
                formatDate(it)
            }
        )
    }

    private fun formatDate(serverDate: String): String? {
        val date = DateTimeUtils.parseServerDate(serverDate) ?: return null
        return DateTimeUtils.formatDate(date)
    }
}
package com.balinasoft.themoviedb.data.repository.movies_repository

import com.balinasoft.themoviedb.data.api.bodyOrError
import com.balinasoft.themoviedb.data.api.interfaces.MoviesApi
import com.balinasoft.themoviedb.data.model.GenreDtoIn
import com.balinasoft.themoviedb.data.model.Language
import com.balinasoft.themoviedb.data.model.MovieDtoIn
import com.balinasoft.themoviedb.data.model.Region

class MoviesRepository(val moviesApi: MoviesApi) : IMoviesRepository {

    override fun getPopularMovies(page: Int, language: Language, region: Region): List<MovieDtoIn> {
        return moviesApi.getPopularMovies(
            page,
            language = language.serverValue,
            region = region.serverValue
        ).bodyOrError().results
    }

    override fun getGenres(language: Language): List<GenreDtoIn> {
        return moviesApi.getGenres(language.serverValue).bodyOrError().genres
    }
}
package com.balinasoft.themoviedb.data.repository.movies_repository

import com.balinasoft.themoviedb.data.model.GenreDtoIn
import com.balinasoft.themoviedb.data.model.Language
import com.balinasoft.themoviedb.data.model.MovieDtoIn
import com.balinasoft.themoviedb.data.model.Region

interface IMoviesRepository {

    fun getPopularMovies(page: Int, language: Language, region: Region): List<MovieDtoIn>

    fun getGenres(language: Language = Language.ENG): List<GenreDtoIn>

}
package com.balinasoft.themoviedb.data.api.interfaces

import com.balinasoft.themoviedb.data.model.GenresListDtoIn
import com.balinasoft.themoviedb.data.model.MovieDtoIn
import com.balinasoft.themoviedb.data.model.PageResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("page") page: Int,
        @Query("language") language: String,
        @Query("region") region: String
    ): Call<PageResult<MovieDtoIn>>


    @GET("genre/movie/list")
    fun getGenres(@Query("language") language: String): Call<GenresListDtoIn>

}
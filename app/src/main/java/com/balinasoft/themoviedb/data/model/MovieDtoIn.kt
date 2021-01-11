package com.balinasoft.themoviedb.data.model

class MovieDtoIn(
    val id: Int,
    val original_title: String,
    val original_language: String,
    val title: String,
    val backdrop_path: String,
    val popularity: Double,
    val vote_count: Int,
    val vote_average: Double,
    val poster_path: String,
    val overview: String,
    val release_date: String,
    val genre_ids: List<Int>
)
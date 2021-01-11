package com.balinasoft.themoviedb.data.model

import com.google.gson.annotations.SerializedName

class PageResult<T>(
    val page: Int,

    @SerializedName("total_results")
    val totalResults: Int,

    @SerializedName("vote_average")
    val total_pages: Int,

    val results: List<T>
)
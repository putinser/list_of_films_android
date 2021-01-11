package com.balinasoft.themoviedb.mvp.base.list


interface ListInteractor<ITEM_VIEW : Any> {

    suspend fun load(page: Int, search: String? = null): List<ITEM_VIEW>


}

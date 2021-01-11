package com.balinasoft.themoviedb.mvp.movies_list

import com.arellomobile.mvp.InjectViewState
import com.balinasoft.themoviedb.di.DaggerUtils
import com.balinasoft.themoviedb.mvp.base.list.ListInteractor
import com.balinasoft.themoviedb.mvp.base.list.PaginatorListPresenter

@InjectViewState
class MoviesListPresenter : PaginatorListPresenter<MoviesListView, MovieItem>() {

    val interactor: PopularListInteractor = PopularListInteractor()

    init {
        DaggerUtils.appComponent.inject(this)
    }

    override fun getInteractor(): ListInteractor<MovieItem> {
        return interactor
    }

}
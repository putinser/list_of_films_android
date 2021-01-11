package com.balinasoft.themoviedb.mvp.movies_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.balinasoft.themoviedb.R
import com.balinasoft.themoviedb.common.view.list.item_decoration.LineItemDecorator
import com.balinasoft.themoviedb.mvp.base.base_screen.applicationBar.StandardApplicationBarViewHolder
import com.balinasoft.themoviedb.mvp.base.list.ReadyListFragment
import com.balinasoft.themoviedb.mvp.movies_list.adapter.MoviesAdapter
import com.vmeste.app.mvp.base.baseScreen.applicationBar.ApplicationBarViewHolder

class MoviesListFragment() : ReadyListFragment<MoviesListView, MoviesListPresenter, MovieItem>(),
    MoviesListView {

    @InjectPresenter
    override lateinit var presenter: MoviesListPresenter

    private val adapter = MoviesAdapter().also {
        it.onReloadButtonClicked = {
            presenter.loadNextPage()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setApplicationBarTitle(getString(R.string.popular_movies_title))
    }

    override fun getAdapter(): RecyclerView.Adapter<*> {
        return adapter
    }

    override fun updateItems(items: List<MovieItem>) {
        adapter.setData(items)
    }

    override fun showProgressInList(show: Boolean) {
        adapter.showProgressBar(show)
    }

    override fun showMessageInList(show: Boolean, message: String?) {
        adapter.showErrorMessage(show, message)
    }

    override fun applyItemDecorator(recyclerView: RecyclerView) {
        super.applyItemDecorator(recyclerView)
        recyclerView.addItemDecoration(LineItemDecorator(context!!, false))
    }

    override fun getApplicationBarViewHolder(
        layoutInflater: LayoutInflater,
        container: ViewGroup
    ): ApplicationBarViewHolder? {
        return StandardApplicationBarViewHolder(layoutInflater, container)
    }
}

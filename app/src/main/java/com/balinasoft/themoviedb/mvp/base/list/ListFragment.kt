package com.balinasoft.themoviedb.mvp.base.list

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.balinasoft.themoviedb.common.view.list.EndlessScrollListener
import com.balinasoft.themoviedb.mvp.base.base_screen.BaseScreenFragment
import com.balinasoft.themoviedb.mvp.base.base_screen.BaseScreenPresenter
import org.jetbrains.anko.support.v4.onRefresh

abstract class ListFragment<VIEW : ListView<ITEM>, PRESENTER : BaseScreenPresenter<VIEW>, ITEM : Any>() :
    BaseScreenFragment<VIEW, PRESENTER>(),
    ListView<ITEM> {

    protected abstract fun getAdapter(): RecyclerView.Adapter<*>

    private var endlessScrollListener: MyEndlessScrollListener? = null

    inner class MyEndlessScrollListener(layoutManager: LinearLayoutManager) :
        EndlessScrollListener(layoutManager) {
        override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
            val presenter = presenter
            if (presenter is PaginatorListPresenter<*, *>) {
                presenter.loadNextPage()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getRecyclerView().also {

            val layoutManager = getLayoutManager()

            if (layoutManager is LinearLayoutManager) {
                endlessScrollListener = MyEndlessScrollListener(layoutManager)
                it.addOnScrollListener(endlessScrollListener!!)
            }

            it.adapter = getAdapter()
            it.layoutManager = layoutManager
            applyItemDecorator(it)
        }

        getSwipeRefreshLayout()?.onRefresh {
            getSwipeRefreshLayout()?.isRefreshing = presenter.refresh()
        }
    }

    open fun getLayoutManager(): RecyclerView.LayoutManager {
        return LinearLayoutManager(context)
    }

    open fun applyItemDecorator(recyclerView: RecyclerView) {}

    abstract fun getRecyclerView(): RecyclerView

    abstract fun getSwipeRefreshLayout(): SwipeRefreshLayout?

    override fun scrollToTop() {
        getRecyclerView().layoutManager?.scrollToPosition(0)
    }

    override fun resetPagination() {
        endlessScrollListener?.resetState()
    }

    override fun showProgressBar(show: Boolean) {
        super.showProgressBar(show)
    }

    override fun onLoadDone() {
        getSwipeRefreshLayout()?.isRefreshing = false
    }

    override fun updateList() {
        getAdapter().notifyDataSetChanged()
    }
}


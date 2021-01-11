package com.balinasoft.themoviedb.mvp.base.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.balinasoft.themoviedb.R
import com.balinasoft.themoviedb.common.extension.enable
import com.balinasoft.themoviedb.mvp.base.base_screen.BaseScreenPresenter
import kotlinx.android.synthetic.main.fragment_list_fragment.*

abstract class ReadyListFragment<VIEW : ListView<ITEM>, PRESENTER : BaseScreenPresenter<VIEW>, ITEM : Any>() :
    ListFragment<VIEW, PRESENTER, ITEM>() {

    protected var searchEnabled: Boolean = false


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getSwipeRefreshLayout()?.enable()
    }

    override fun provideYourView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_list_fragment, container, false)
    }

    override fun getRecyclerView(): RecyclerView {
        return recyclerView
    }

    override fun getSwipeRefreshLayout(): SwipeRefreshLayout? {
        return getPlaceholderSwipeRefresh()
    }
}



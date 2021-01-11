package com.balinasoft.themoviedb.mvp.movies_list

import androidx.fragment.app.Fragment
import com.balinasoft.themoviedb.mvp.base.BaseFragmentActivity

class MoviesListActivity() : BaseFragmentActivity() {

    override fun getFragment(): Fragment {
        return MoviesListFragment()
    }
}
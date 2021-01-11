package com.balinasoft.themoviedb.mvp.base.base_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.arellomobile.mvp.MvpAppCompatFragment
import com.balinasoft.themoviedb.R
import com.balinasoft.themoviedb.common.dialogs.ProgressBarDialog
import com.balinasoft.themoviedb.common.extension.visibleOrInvisible

import com.balinasoft.themoviedb.mvp.base.placeholder.NewPlaceholderLayout

import com.vmeste.app.mvp.base.baseScreen.BaseScreenView
import com.vmeste.app.mvp.base.baseScreen.applicationBar.ApplicationBarViewHolder
import kotlinx.android.synthetic.main.fragment_base_screen.*
import kotlinx.android.synthetic.main.fragment_base_screen.view.*

abstract class BaseScreenFragment<VIEW : BaseScreenView, PRESENTER : BaseScreenPresenter<VIEW>>() :
    MvpAppCompatFragment(), BaseScreenView {

    open lateinit var presenter: PRESENTER

    private var progressBarDialog: ProgressBarDialog? = null

    private var applicationBarText: String? = null

    var applicationBarViewHolder: ApplicationBarViewHolder? = null
        private set

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_base_screen, container, false)

        val viewToAttach = provideYourView(inflater, view.payloadContainer, savedInstanceState)
        view.payloadContainer.addView(viewToAttach)

        val applicationBar = buildApplicationBar(inflater, view.applicationBarContainer)

        if (applicationBar != null) {
            view.applicationBarContainer.addView(applicationBar.view)
            view.applicationBarShadow.visibleOrInvisible(applicationBar.showShadow)
        }

        view.applicationBarShadow.visibleOrInvisible(false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getPlaceholderView().onRefreshButtonClicked = {
            presenter.refresh()
        }
    }

    private fun buildApplicationBar(
        inflater: LayoutInflater,
        container: ViewGroup
    ): ApplicationBarView? {
        /* if (whereShowApplicationBar != PlaceToShowApplicationBar.IN_FRAGMENT) {
             return null
         }*/

        applicationBarViewHolder = getApplicationBarViewHolder(inflater, container)
        applicationBarViewHolder?.let {

            it.setBackButtonVisibility(needToShowBackButton())
            it.onBackButtonClicked = {
                exit()
            }
            it.setText(applicationBarText ?: "")
            return ApplicationBarView(it.getView(), true)
        }

        return null
    }

    open fun getApplicationBarViewHolder(
        layoutInflater: LayoutInflater,
        container: ViewGroup
    ): ApplicationBarViewHolder? {
        return null
    }

    abstract fun provideYourView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View

    fun getPlaceholderSwipeRefresh(): SwipeRefreshLayout {
        return placeholder_swipe_refresh
    }

    open fun getPlaceholderView(): NewPlaceholderLayout {
        return new_placeholder_layout
    }

    override fun showNoInternetPlaceholder(show: Boolean, text: String) {
        getPlaceholderView().showMessageWithReloadButton(
            show,
            text,
            getString(R.string.try_again_bt_text)
        )
    }

    override fun showProgressBar(show: Boolean) {
        getPlaceholderView().showProgressBar(show)
    }

    override fun setApplicationBarTitle(title: String) {
        applicationBarText = title
        applicationBarViewHolder?.setText(title)
    }

    override fun showFullContentMessage(show: Boolean, message: String) {
        getPlaceholderView().showMessage(show, message)
    }

    override fun showProgressBarDialog(show: Boolean, message: String?) {
        progressBarDialog?.dismiss()
        if (show) {
            val newDialog = ProgressBarDialog(context!!, message)
            newDialog.show()
            progressBarDialog = newDialog
        }
    }

    class ApplicationBarView(
        val view: View,
        val showShadow: Boolean
    ) {
        val showMargin: Boolean = true
    }

    open fun exit() {
        activity?.finish()
    }

    open fun needToShowBackButton(): Boolean {
        return !isRootScreen()
    }

    fun isRootScreen(): Boolean {
        val isRootFragment = isRootFragment()
        val isRootActivity = isRootActivity()

        if (isRootFragment == null) {
            return isRootActivity
        }
        return if (isRootFragment == false) {
            false
        } else {
            isRootActivity
        }
    }

    protected fun isRootFragment(): Boolean? {
        return null
    }

    protected fun isRootActivity(): Boolean {
        val a = activity
        if (a == null) return true
        return a.isTaskRoot
    }
}
package com.vmeste.app.mvp.base.baseScreen

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType


interface BaseScreenView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showNoInternetPlaceholder(show: Boolean, text: String)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showProgressBar(show: Boolean)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setApplicationBarTitle(title: String)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showFullContentMessage(show: Boolean, message: String)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showProgressBarDialog(show: Boolean, message: String?)
}

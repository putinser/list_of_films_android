package com.balinasoft.themoviedb.mvp.base.list

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.vmeste.app.mvp.base.baseScreen.BaseScreenView

interface ListView<LIST_ITEM> : BaseScreenView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun updateItems(items: List<LIST_ITEM>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun onLoadDone()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun updateList()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun scrollToTop()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun resetPagination()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showProgressInList(show: Boolean)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showMessageInList(show: Boolean, message: String?)

}

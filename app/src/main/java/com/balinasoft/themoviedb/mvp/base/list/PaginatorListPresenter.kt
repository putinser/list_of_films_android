package com.balinasoft.themoviedb.mvp.base.list


import com.balinasoft.themoviedb.R
import com.balinasoft.themoviedb.common.errors.showMessage
import com.balinasoft.themoviedb.data.string.StringProvider
import com.balinasoft.themoviedb.di.DaggerUtils
import com.balinasoft.themoviedb.mvp.base.base_screen.BaseScreenPresenter
import com.vmeste.app.common.extension.launchUI
import kotlinx.coroutines.Job
import java.io.EOFException
import java.util.concurrent.atomic.AtomicBoolean

abstract class PaginatorListPresenter<VIEW : ListView<ITEM>, ITEM : Any> :
    BaseScreenPresenter<VIEW>() {

    lateinit var stringProvider: StringProvider

    init {
        stringProvider = DaggerUtils.appComponent.provideStringProvider()
    }

    abstract fun getInteractor(): ListInteractor<ITEM>

    protected var currentPage: Int = -1

    protected var allLoaded: Boolean = false
        set(value) {
            field = value
            /*viewState.showProgressBarInList(value)*/
        }

    protected var loadingNow: AtomicBoolean = AtomicBoolean(false)

    protected var loadingJob: Job? = null

    protected var needUpdateViewAfterLoad: Boolean = true

    protected var data = ArrayList<ITEM>()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadNext()
    }

    fun loadNextPage() {
        checkAndLoadNextPage()
    }

    private fun checkAndLoadNextPage() {
        loadNext()
    }

    open fun notifyLoadingStateChanged(loading: Boolean) {

    }

    private fun loadNext() {

        if (!allLoaded && loadingNow.compareAndSet(false, true)) {
            val nextPage = currentPage + 1

            notifyLoadingStateChanged(true)
            viewState.showFullContentMessage(false, "")
            viewState.showMessageInList(false, "")
            viewState.showProgressBar(data.isEmpty() && !needUpdateViewAfterLoad)
            viewState.showProgressInList(!allLoaded)

            loadingJob = launchUI {
                val intercator = getInteractor()

                try {
                    val items = intercator.load(nextPage, null)
                    onItemsLoaded(items, nextPage, intercator)
                } catch (e: java.lang.Exception) {
                    onError(e, nextPage)
                }

            }.also {
                it.invokeOnCompletion {
                    loadingNow.set(false)
                    viewState.showProgressBar(false)
                    viewState.onLoadDone()
                    notifyLoadingStateChanged(false)
                }
            }
        }
    }

    private fun onError(e: Exception, pageToLoad: Int) {
        if (e is EOFException) {
            onItemsLoaded(listOf(), pageToLoad, getInteractor())
            return
        }

        viewState.showMessage(e) {
            viewState.showMessageInList(true, it)
        }

        updateItems(data)
    }

    private fun onItemsLoaded(
        page: List<ITEM>,
        pageToLoad: Int,
        interactor: ListInteractor<ITEM>
    ) {
        val items = page

        viewState.showProgressBar(false)
        // viewState.onLoadDone()
        if (items.isEmpty() && pageToLoad == 0) {
            onEmptyListLoaded(true, interactor)
            updateItems(data)
            allLoaded = true
            return
        }
        if (items.isEmpty() && pageToLoad != 0) {
            allLoaded = true
            updateItems(data)
            return
        }
        if (data.isEmpty()) {
            viewState.scrollToTop()
        }
        currentPage = pageToLoad
        addData(items)

        onEmptyListLoaded(false, interactor)

    }

    fun onEmptyListLoaded(isEmpty: Boolean, interactor: ListInteractor<ITEM>) {
        if (isEmpty) {
            viewState.showFullContentMessage(true, getEmptyListText())
        } else {
            viewState.showFullContentMessage(false, "")
        }
    }

    open fun getEmptyListText(): String {
        return stringProvider.getString(R.string.list_is_empty_default)
    }

    private fun addData(items: List<ITEM>) {
        data.addAll(items)
        updateItems(data)
    }

    open fun updateItems(data: List<ITEM>) {
        viewState.updateItems(data)
    }

    override fun refresh(): Boolean {
        super.refresh()
        reset()
        loadNext()
        return true
    }

    private fun reset() {
        data.clear()
        needUpdateViewAfterLoad = true
        loadingJob?.cancel()
        loadingNow.set(false)
        currentPage = -1
        allLoaded = false
        viewState.resetPagination()
    }

    protected fun reloadData() {
        reset()
        loadNext()
    }
}
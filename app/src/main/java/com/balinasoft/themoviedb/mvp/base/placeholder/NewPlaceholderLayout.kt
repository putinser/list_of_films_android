package com.balinasoft.themoviedb.mvp.base.placeholder


import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.balinasoft.themoviedb.R
import com.balinasoft.themoviedb.common.extension.CallBackKUnit
import com.balinasoft.themoviedb.common.extension.gone
import com.balinasoft.themoviedb.common.extension.visibleOrInvisible
import com.parsage.kvazipupa.view.views.placeholder.ProgressBarScreenViewHolder

import com.vmeste.app.common.placeholder.MessageScreenViewHolder


open class NewPlaceholderLayout(context: Context, attributeSet: AttributeSet) :
    FrameLayout(context, attributeSet) {

    private enum class Layouts() {
        PROGRESS, MESSAGE_WITH_RELOAD, MESSAGE
    }

    private var noInternet: NoInternetPlaceholder
    private var progressBar: ProgressBarScreenViewHolder
    private var message: MessageScreenViewHolder

    private var root: View? = null
    private val rootId: Int

    var onRefreshButtonClicked: CallBackKUnit? = null

    private val placeholderViews: HashMap<Layouts, View>

    private val visibilityFlags: HashSet<Layouts> = HashSet()

    override fun addView(child: View?, index: Int, params: ViewGroup.LayoutParams?) {
        super.addView(child, index, params)

        for (i in 0 until childCount) {
            val view = getChildAt(i)
            if (view.id == rootId) {
                root = view
            }
        }

        updatePlaceholdersVisibility()
    }

    open fun getMessagePlaceholderBuilder(): ViewHolderBuilder<MessageScreenViewHolder> {
        return ViewHolderBuilderImpl<MessageScreenViewHolder>() {
            MessageViewHolder(it)
        }
    }

    fun getNoInternetPlaceholder(): ViewHolderBuilder<NoInternetPlaceholder> {
        return ViewHolderBuilderImpl<NoInternetPlaceholder>() {
            NoInternetPlaceholder(it)
        }
    }

    init {
        val attributes =
            context.obtainStyledAttributes(attributeSet, R.styleable.NewPlaceholderLayout)


        rootId = attributes.getResourceId(R.styleable.NewPlaceholderLayout_root_id, -1)

        if (rootId == -1) {
            throw IllegalStateException("Root id argument is not set")
        }

        noInternet = getNoInternetPlaceholder().provide(this).also {
            it.setOnReload() {
                onRefreshButtonClicked?.invoke()
            }
        }
        progressBar = StandartProgressBarScreen(this)
        message = getMessagePlaceholderBuilder().provide(this)

        placeholderViews = hashMapOf<Layouts, View>(
            Layouts.PROGRESS to progressBar.getView(),
            Layouts.MESSAGE to message.getView(),
            Layouts.MESSAGE_WITH_RELOAD to noInternet.getView()
        )

        placeholderViews.forEach {
            this.addView(it.value)
            it.value.gone()
        }

        attributes.recycle()
    }


    fun showMessage(show: Boolean, message: String?) {
        this.message.setMessage(message ?: "")
        setVisibilityFlag(Layouts.MESSAGE, show)
        updatePlaceholdersVisibility()
    }

    fun showMessageWithReloadButton(show: Boolean, message: String, reloadButtonText: String) {
        this.noInternet.setMessage(message)
        this.noInternet.setButtonText(reloadButtonText)
        this.setVisibilityFlag(Layouts.MESSAGE_WITH_RELOAD, show)
        updatePlaceholdersVisibility()
    }

    fun showProgressBar(show: Boolean) {

        this.setVisibilityFlag(Layouts.PROGRESS, show)
        updatePlaceholdersVisibility()
    }

    private fun setVisibilityFlag(layout: Layouts, set: Boolean) {
        if (set) {
            visibilityFlags.add(layout)
        } else {
            visibilityFlags.remove(layout)
        }
    }

    private fun getCurrentViewToShow(): Layouts? {
        return visibilityFlags.sortedBy { it.ordinal }.firstOrNull()
    }

    private fun updatePlaceholdersVisibility() {
        setupVisibility(getCurrentViewToShow())
    }

    private fun setupVisibility(layout: Layouts?) {
        placeholderViews.forEach { it ->
            updateVisibility(it.value, it.key == layout)
        }

        root?.let {
            updateVisibility(it, layout == null)
        }
    }

    private fun updateVisibility(view: View, visible: Boolean) {
        val isVisible = view.visibility == View.VISIBLE
        if (isVisible != visible) {
            view.visibleOrInvisible(visible)
        }
    }

    fun isShowingLayout(): Boolean {
        return getCurrentViewToShow() != null
    }

}
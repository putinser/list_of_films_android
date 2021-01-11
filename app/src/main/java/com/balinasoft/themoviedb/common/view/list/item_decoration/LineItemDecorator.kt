package com.balinasoft.themoviedb.common.view.list.item_decoration

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.balinasoft.themoviedb.R


class LineItemDecorator :
    DividerItemDecoration {

    private lateinit var context: Context

    companion object {
        val VERTICAL = DividerItemDecoration.VERTICAL
        val HORIZONTAL = DividerItemDecoration.HORIZONTAL

        private val DEFAULT_DIVIDER = R.drawable.line_divider
    }

    constructor(context: Context, showLastItem: Boolean, spaceLeft: Int, spaceRight: Int) : this(
        context,
        showLastItem
    ) {
        setMarginLeft(spaceLeft)
        setMarginRight(spaceRight)
    }

    constructor(context: Context, showLastItem: Boolean) : super(
        context,
        DividerItemDecoration.VERTICAL,
        showLastItem
    ) {
        initialize(context, DEFAULT_DIVIDER)
    }

    constructor(
        context: Context,
        orientation: Int,
        showLastItem: Boolean, @DrawableRes divider: Int = DEFAULT_DIVIDER
    ) : super(context, orientation, showLastItem) {
        initialize(context, divider)
    }

    private fun initialize(context: Context, @DrawableRes divider: Int) {
        this.context = context
        setDrawable(ContextCompat.getDrawable(context, divider)!!)
    }

    /* fun useDefaultMargin() {
         val left = context.resources.getDimension(R.dimen.screen_padding_left)
         val right = context.resources.getDimension(R.dimen.screen_padding_right)
         setMarginLeft(left.toInt())
         setMarginRight(right.toInt())
     }*/
}

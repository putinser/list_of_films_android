package com.balinasoft.themoviedb.common.extension

import android.app.Activity
import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.content.res.Resources
import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StyleSpan
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.widget.AppCompatSpinner
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import org.jetbrains.anko.toast


//@Suppress("UNCHECKED_CAST")
//fun <T> Activity.findView(localId: Int): Lazy<T> = lazy { this.findViewById(localId) as T }
//
//@Suppress("UNCHECKED_CAST")
//fun <T> View.findView(localId: Int): Lazy<T> = lazy { this.findViewById(localId) as T }
//
//@Suppress("UNCHECKED_CAST")
//fun <T> Fragment.findView(localId: Int): Lazy<T> = lazy { this.view!!.findViewById(localId) as T }

typealias CallBackK<T> = (T) -> Unit
typealias CallBackKUnit = () -> Unit

fun Fragment.setupUI(view: View) {
    activity?.setupUI(view)
}

fun Activity.setupUI(view: View? = this.window.decorView.rootView) {

    if (view != null && !(view is TextView || view is ImageView)) {
        view.requestFocus()
        view.setOnTouchListener { v, _ ->
            v.requestFocus()
            hideSoftKeyboard()
            false
        }
    }

    if (view is ViewGroup) {
        for (i in 0..view.childCount - 1) {
            val innerView = view.getChildAt(i)
            setupUI(innerView)
        }
    }

    view?.requestFocus()

}

fun Activity.hideSoftKeyboard() {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    val view = currentFocus
    if (view != null) {
        inputMethodManager.hideSoftInputFromWindow(
            view.windowToken,
            InputMethodManager.HIDE_NOT_ALWAYS
        )
    }
}

fun Context.showSoftKeyboard(view: View) {
    val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
    view.requestFocus()
    inputMethodManager.showSoftInput(view, 0)
}


/**
 * Convert dp to pixel
 */
val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

/**
 * Convert dp to pixel
 */
val Float.dp: Float
    get() = (this * Resources.getSystem().displayMetrics.density)


fun SwipeRefreshLayout.setRefresh(refresh: Boolean) {
    if (refresh) {
        this.isRefreshing = refresh
    } else {
        this.post { this.isRefreshing = refresh }
    }
}

/*
fun Resources.Theme.isWindowActionBar(): Boolean {
    val typedValue = TypedValue()
    this.resolveAttribute(R.attr.windowActionBar, typedValue, true)
    return typedValue.data == 0
}
*/

fun Resources.getSpannableBoldString(stringId: Int, string: String): SpannableString {
    val spannableString = SpannableString(this.getString(stringId, string))
    spannableString.setSpan(
        StyleSpan(Typeface.BOLD),
        spannableString.indexOf(string),
        spannableString.length,
        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    return spannableString
}


fun <T> AppCompatSpinner.setAdapterWithPositionWithCustomView(
    list: List<T>, onItemSelected: (item: T, position: Int) -> Unit,
    firstValue: T? = null, isFirstPositionColorGray: Boolean = false,
    layoutId: Int = 0, textViewId: Int
) {
    this.apply {

        val adapterList = kotlin.collections.mutableListOf<T>()
        firstValue?.let {
            adapterList.add(it)
        }
        adapterList.addAll(list)


        adapter = ArrayAdapter<T>(this.context, layoutId, textViewId, adapterList).apply {
            setDropDownViewResource(layoutId)
        }
        onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                onItemSelected.invoke(adapterList[position], position)

                if (isFirstPositionColorGray) {
                    parent?.findViewById<TextView>(textViewId)?.apply {
                        setTextColor(if (position == 0) Color.GRAY else Color.BLACK)
                    }
                }

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
    }
}

fun Activity.setStatusBarColor(id: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        window?.statusBarColor = ContextCompat.getColor(this, id)
    }
}

fun Activity.setLightStatusBar() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }
}

fun Activity.clearLightStatusBar() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        window.decorView.systemUiVisibility =
            window.decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
    }
}

fun Context.showKB(view: View) {
    val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager?
    imm!!.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
}

fun Context.hideKB(et: EditText) {
    et.clearFocus();
    val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(et.windowToken, 0)
}

fun TextView.checkEmptyAndShowMessage(message: String): Boolean {
    if (text.toString().isEmpty()) {
        context.toast(message)
        return true
    }
    return false
}
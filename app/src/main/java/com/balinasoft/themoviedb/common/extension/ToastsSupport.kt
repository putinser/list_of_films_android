@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.balinasoft.themoviedb.common.extension

import android.widget.Toast
import androidx.fragment.app.Fragment
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast


/**
 * Display the simple Toast message with the [Toast.LENGTH_SHORT] durationStringValue.
 *
 * @param message the message text resource.
 */
inline fun Fragment.toast(message: Int): Unit? =
    activity?.runOnUiThread { activity!!.toast(message) }


/**
 * Display the simple Toast message with the [Toast.LENGTH_SHORT] durationStringValue.
 *
 * @param message the message text.
 */
inline fun Fragment.toast(message: CharSequence): Unit? =
    activity?.runOnUiThread { activity!!.toast(message) }


/**
 * Display the simple Toast message with the [Toast.LENGTH_LONG] durationStringValue.
 *
 * @param message the message text resource.
 */
inline fun Fragment.longToast(message: Int): Unit? =
    activity?.runOnUiThread { activity!!.longToast(message) }

/**
 * Display the simple Toast message with the [Toast.LENGTH_LONG] durationStringValue.
 *
 * @param message the message text.
 */
inline fun Fragment.longToast(message: CharSequence): Unit? =
    activity?.runOnUiThread { activity!!.longToast(message) }

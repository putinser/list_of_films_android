package com.balinasoft.themoviedb.common.extension

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat


fun Context.isIntentAvailable(action: String): Boolean {
    return this.packageManager.queryIntentActivities(
        Intent(action),
        PackageManager.MATCH_DEFAULT_ONLY
    ).size > 0
}

val Context.notificationManager: NotificationManager get() = this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
fun Context.launchBrowser(uri: Uri) {
    val intents = Intent(Intent.ACTION_VIEW, uri)
    val b = Bundle()
    b.putBoolean("new_window", true)
    intents.putExtras(b)
    startActivity(intents)
}

fun Context.launchPhone(phoneNumber: String) {
    try {
        val intents = Intent(Intent.ACTION_VIEW, Uri.parse("tel:" + phoneNumber))
        val b = Bundle()
        b.putBoolean("new_window", true)
        intents.putExtras(b)
        startActivity(intents)
    } catch (e: Exception) {

    }

}

fun Context.launchMap(address: String) {
    try {
        val geoUri = "http://maps.google.com/maps?q=loc:$address"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(geoUri))
        startActivity(intent)
    } catch (e: Exception) {

    }
}


fun Context.getColorCompat(@ColorRes color: Int): Int {
    return ContextCompat.getColor(this, color)
}
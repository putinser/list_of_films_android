package com.balinasoft.themoviedb.common.utils

import java.text.SimpleDateFormat
import java.util.*

object DateTimeUtils {

    val DATE_SERVER_FORMAT = "yyyy-MM-dd"
    val DATE_USER_FORMAT = "dd.MM.yyyy"


    fun parseServerDate(serverDate: String): Date? {
        return SimpleDateFormat(DATE_SERVER_FORMAT, Locale.US).parse(serverDate)
    }

    fun formatDate(date: Date): String {
        return SimpleDateFormat(DATE_USER_FORMAT, Locale.US).format(date)
    }

}
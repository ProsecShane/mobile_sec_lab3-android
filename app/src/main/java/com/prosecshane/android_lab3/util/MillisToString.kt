package com.prosecshane.android_lab3.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun millisToString(millis: Long): String {
    val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH)
    val date = Date(millis)
    return dateFormat.format(date)
}

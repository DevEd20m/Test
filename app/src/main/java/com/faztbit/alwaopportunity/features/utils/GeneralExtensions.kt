package com.faztbit.alwaopportunity.features.utils

import java.util.Calendar
import java.util.Date

fun calculateDateInMillis(years: Int, months: Int = 0): Long {
    val calendar = Calendar.getInstance()
    val now = Date(System.currentTimeMillis())
    calendar.time = now
    calendar.add(Calendar.YEAR, years)
    if (months != 0) {
        calendar.add(Calendar.MONTH, months)
    }
    return calendar.timeInMillis
}

fun appendZeroIfNeeded(x: Int): String = if (x.toString().length == 1) "0$x" else "$x"
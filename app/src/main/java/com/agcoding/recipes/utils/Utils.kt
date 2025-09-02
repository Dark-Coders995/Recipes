package com.agcoding.recipes.utils


import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

fun formatDate(timestamp: Int): String {
    val formatter = DateTimeFormatter.ofPattern("EEE, MMM d", Locale.US)
    val dateTime = Instant.ofEpochSecond(timestamp.toLong()).atZone(ZoneId.systemDefault())
    return formatter.format(dateTime)
}

fun formatDateTime(timestamp: Int): String {
    val formatter = DateTimeFormatter.ofPattern("hh:mm a", Locale.US)
    val dateTime = Instant.ofEpochSecond(timestamp.toLong()).atZone(ZoneId.systemDefault())
    return formatter.format(dateTime)
}

fun formatDecimals(item: Double): String {
    return " %.0f".format(item)
}


fun extractSizeFromUrl(url: String): Pair<Int, Int>? {
    // Example: https://.../665540-556x370.jpg
    val regex = Regex("-(\\d+)x(\\d+)\\.")
    val match = regex.find(url)
    return match?.groupValues?.let {
        Pair(it[1].toInt(), it[2].toInt())
    }
}
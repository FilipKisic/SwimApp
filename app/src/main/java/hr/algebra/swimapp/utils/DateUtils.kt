package hr.algebra.swimapp.utils

import java.text.SimpleDateFormat
import java.util.*

fun Date.format(format: String, locale: Locale = Locale.getDefault()): String{
    val formatter = SimpleDateFormat(format, locale)
    return formatter.format(this)
}

fun getCurrentDateTime(): Date{
    return Calendar.getInstance().time
}
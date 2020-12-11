package hr.algebra.swimapp.framework

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.preference.PreferenceManager


inline fun <reified T : Activity> Context.startActivity() =
    startActivity(Intent(this, T::class.java))

fun Context.getBooleanPreference(key: String) =
    PreferenceManager.getDefaultSharedPreferences(this).getBoolean(key, false)

fun Context.isOnline(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = connectivityManager.activeNetwork
    if (network != null) {
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
        if (networkCapabilities != null) {
            return networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
        }
    }
    return false
}
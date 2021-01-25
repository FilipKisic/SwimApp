package hr.algebra.swimapp.framework

import android.app.Activity
import android.app.AlertDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.preference.PreferenceManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import hr.algebra.swimapp.components.SessionGoalCard
import kotlinx.android.synthetic.main.session_goal_card.view.*


inline fun <reified T : Activity> Context.startActivity() = Intent(this, T::class.java).apply {
    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    startActivity(this)
}
inline fun <reified T : Activity> Context.startPreviousActivity() = Intent(this, T::class.java).apply {
    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
    startActivity(this)
}
inline fun <reified T : Activity> Fragment.startActivity() = startActivity(Intent(requireContext(), T::class.java))
inline fun <reified T : BroadcastReceiver> Context.sendBroadcast() = sendBroadcast(Intent(this, T::class.java))

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

fun SessionGoalCard.isValid(context: Context, unit: String): Boolean{
    if(this.etInput.text.toString().toInt() <= 0){
        Toast.makeText(context, "$unit value must be greater than 0", Toast.LENGTH_LONG).show()
        return false
    }
    return true
}
package hr.algebra.swimapp.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import hr.algebra.swimapp.DATA_IMPORTED
import hr.algebra.swimapp.MainActivity
import hr.algebra.swimapp.framework.setBooleanPreference
import hr.algebra.swimapp.framework.startActivity

class WeatherReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        context.setBooleanPreference(DATA_IMPORTED, true)
        context.startActivity<MainActivity>()
    }

}
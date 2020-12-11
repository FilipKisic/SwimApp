package hr.algebra.swimapp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import hr.algebra.swimapp.framework.getBooleanPreference
import hr.algebra.swimapp.framework.isOnline
import hr.algebra.swimapp.framework.startActivity

private const val DELAY = 6000L
private const val DATA_IMPORTED = "hr.algebra.swimapp.data_imported"

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        window.decorView.apply {
            systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
        }
        loadData()
    }

    private fun loadData() {
        if (getBooleanPreference(DATA_IMPORTED)) {
            Handler(Looper.getMainLooper()).postDelayed({
                startActivity<MainActivity>()
            }, DELAY)
        } else {
            if (isOnline()) {
                Handler(Looper.getMainLooper()).postDelayed({
                    startActivity<MainActivity>()
                }, DELAY)
            } else {
                Toast.makeText(this, getString(R.string.no_connection_avaliable), Toast.LENGTH_LONG).show()
                finish()
            }
        }
    }
}
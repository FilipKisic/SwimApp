package hr.algebra.swimapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hr.algebra.swimapp.components.SwimInfoCard
import hr.algebra.swimapp.framework.startActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.swim_info_card.view.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initListeners()
        /*Will be removed, just test method*/
        /*!!RECYCLER VIEW!!*/
        addTwoWidgets()
    }

    private fun initListeners() {
        ivNewSession.setOnClickListener {
            startActivity<NewSession>()
        }
    }

    private fun addTwoWidgets() {
        val session = SwimInfoCard(this)
        session.tvLapCount.text = "40 laps"
        session.tvDistance.text  = "2000 m"
        session.tvDayInWeek.text = "Tuesday"
        session.tvTime.text = "01:12:45"

        val sessionTwo = SwimInfoCard(this)
        sessionTwo.tvLapCount.text = "10 laps"
        sessionTwo.tvDistance.text  = "500 m"
        sessionTwo.tvDayInWeek.text = "Wednesday"
        sessionTwo.tvTime.text = "00:10:32"

        session_list.addView(session)
        session_list.addView(sessionTwo)
    }
}
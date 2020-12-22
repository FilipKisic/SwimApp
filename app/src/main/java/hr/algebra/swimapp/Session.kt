package hr.algebra.swimapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.action_button.view.*
import kotlinx.android.synthetic.main.activity_session.*

private lateinit var buttonState: String

class Session : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_session)
        init()
    }

    private fun init() {
        btnStartStop.setOnClickListener {
            buttonState = btnStartStop.tvButtonText.text.toString()
            toggleState()
            //on start start session, on stop finish session and add card to recycleView
        }
    }

    private fun toggleState() {
        btnStartStop.tvButtonText.setText(if (buttonState == "Start") R.string.stop else R.string.start)
        btnStartStop.buttonBackgroundColor =
            if (buttonState == "Start") resources.getDrawable(R.drawable.button_background_yellow, theme) else resources.getDrawable(R.drawable.button_background_blue, theme)
    }
}
package hr.algebra.swimapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import hr.algebra.swimapp.framework.startActivity
import kotlinx.android.synthetic.main.activity_new_session.*
import kotlinx.android.synthetic.main.session_goal_card.view.*

class NewSession : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_session)
        initListeners()
    }

    private fun initListeners() {
        btnStart.setOnClickListener {
            //validate input
            //save data
            //open session details
            startActivity<Session>()
        }
    }
}
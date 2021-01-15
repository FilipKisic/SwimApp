package hr.algebra.swimapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.nisrulz.sensey.Sensey
import kotlinx.android.synthetic.main.action_button.view.*
import kotlinx.android.synthetic.main.activity_session.*
import timerx.Stopwatch
import timerx.StopwatchBuilder
import timerx.Timer
import timerx.TimerBuilder
import java.util.concurrent.TimeUnit

private var buttonState = "Start"
private var goalDistance = 0
private var currentDistance = 0
private var laps = 0
private var togo = 0
private var repetitions = 0
private var pause = 0
private lateinit var stopwatch: Stopwatch
private lateinit var timer: Timer
private var isActive = false

class Session : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_session)
        Sensey.getInstance().init(this)
        init()
        initListeners()
    }

    private fun init() {
        getData()
        showData()
        initStopwatchAndTimer()
    }

    private fun getData() {
        togo = intent.getIntExtra("repDistance", 0)
        repetitions = intent.getIntExtra("repetitions", 0)
        goalDistance = togo * repetitions
        pause = intent.getIntExtra("pause", 0)
        /*println("GET DATA CALLED")
        println("TOGO: $togo")
        println("GOAL: $goalDistance")
        println("PAUSE: $pause")*/
    }

    private fun showData() {
        tvTotalLength.text = currentDistance.toString()
        tvLaps.text = laps.toString()
        tvToGo.text = togo.toString()
        tvPause.text = if (pause < 10) "0$pause:00" else "$pause:00"
    }

    private fun initStopwatchAndTimer() {
        stopwatch = StopwatchBuilder().apply {
            startFormat("HH:MM:SS")
            onTick { time -> tvStopwatch.text = time }
        }.build()
        timer = TimerBuilder().apply {
            startTime(pause.toLong(), TimeUnit.MINUTES)
            startFormat("MM:SS")
            onTick { time -> tvPause.text = time }
            onFinish { resumeSession() }
        }.build()
        timer.start()
        timer.stop()
    }

    private fun initListeners() {
        btnStartStop.setOnClickListener {
            toggleState()
            buttonState = btnStartStop.tvButtonText.text.toString()
            if (buttonState == "Stop") resumeSession() else pauseSession()
            //if(isActive) stopwatch.stop() else resumeSession()
        }
        Sensey.getInstance().startWaveDetection {
            if (buttonState == "Stop") {
                currentDistance += 50
                laps++
                togo -= 50
                checkState()
                showData()
            }
            /*if(isActive){
                currentDistance += 50
                laps++
                togo -= 50
                checkState()
                showData()
            }*/
        }
    }

    private fun checkState() {
        if (currentDistance != goalDistance) {
            if (togo == 0) {
                toggleState()
                pauseSession()
                getData()
            }
            pb_swim_goal.progress = ((currentDistance.toFloat() / goalDistance.toFloat()) * 100).toInt()
        } else {
            pb_swim_goal.progress = 100
            stopwatch.stop()
            btnStartStop.isEnabled = false
            //create card in recycler view
        }
    }

    private fun pauseSession(){
        toggleState()
        stopwatch.stop()
        timer.start()
    }

    private fun resumeSession(){
        toggleState()
        stopwatch.start()
        timer.stop()
    }

    private fun toggleState() = btnStartStop.tvButtonText.setText(if (buttonState == "Start") R.string.stop else R.string.start)
    /*private fun toggleState(){
        isActive = if(buttonState == "Start"){
            btnStartStop.tvButtonText.setText(R.string.stop)
            false
        } else{
            btnStartStop.tvButtonText.setText(R.string.start)
            true
        }
    }*/
}
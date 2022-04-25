package com.example.eggclock_andriod_h4

import android.annotation.SuppressLint
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.time.format.DateTimeFormatter

val isTimerStarted = false // TODO: marby move later on
var timeChosen = 0;

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide() // removes the actionbar
        setContentView(R.layout.activity_main)

    }

    fun timer(numOfMin:Int){
        val textView_timer = findViewById<TextView>(R.id.textView_timer)

        val timeInSec : Long =  (numOfMin * 60).toLong()
        val timeChoseMiliSec : Long = (timeInSec * 1000).toLong()
        val countDownInterval : Long = 1000

        val tag = "timer_loged"

        val countDownTimer =  object : CountDownTimer(timeChoseMiliSec, countDownInterval){
            override fun onTick(millisUntilFinished: Long) {
                Log.d(tag, "${millisUntilFinished / 1000}")
                var timeLeft = convertMiliSecToMinAndSec(millisUntilFinished)
                textView_timer.setText(timeLeft.format(2))
            }

            override fun onFinish() {
                Toast.makeText(applicationContext, "your egg is finished",Toast.LENGTH_LONG).show()
            }
        }
        countDownTimer.start()

    }

    fun convertMiliSecToMinAndSec(milisec:Long): String{

        val allSeconds = (milisec / 1000) + 1
        val minutes = allSeconds / 60
        val seconds = Math.abs((minutes * 60) - allSeconds)

        val time = "$minutes:$seconds"
        return time
    }

    fun onBtnEggSelectedClicked(view: View){
        val btn_startTimer = findViewById<Button>(R.id.btn_start) // TODO: double code
        val timeToSoftBoilInMin = 5
        val timeToMediumBoilInMin = 7
        val timeToHardBoilInMin = 10


        when (view.getId()) {
            R.id.btn_softEgg -> timeChosen = timeToSoftBoilInMin
            R.id.btn_midiumEgg -> timeChosen = timeToMediumBoilInMin
            R.id.btn_hardEgg -> timeChosen = timeToHardBoilInMin

            else -> throw RuntimeException("Unknow btn id")
        }

        btn_startTimer.isEnabled = true
    }

    fun onClikStartTime(view: View){
        val btn_startTimer = findViewById<Button>(R.id.btn_start) // TODO: double code

        btn_startTimer.isEnabled = false

        if (timeChosen > 0) {
            timer(timeChosen)
        }
        else{
            Toast.makeText(applicationContext,"Pleace chosse an time", Toast.LENGTH_SHORT)

        }


    }
}


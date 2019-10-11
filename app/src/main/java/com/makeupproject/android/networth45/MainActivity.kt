package com.makeupproject.android.networth45

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    //create a timer for splash screen
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val countDownTimer = object : CountDownTimer(2000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
            }

            override fun onFinish() {
                //Change the activity when the timer is done
                val intent = Intent(this@MainActivity, WelcomeActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        countDownTimer.start()
    }
}

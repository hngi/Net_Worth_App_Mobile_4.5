package com.makeupproject.android.networth45

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class CalculatorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)
    }

    fun changeActionBarText(title: String) {
        supportActionBar?.title = title
    }
}
package com.makeupproject.android.networth45

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class FinallyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finally)
    }

    fun goToCalculator(view: View) {
        val intent = Intent(this@FinallyActivity, CalculatorActivity::class.java)
        startActivity(intent)
    }
}

package com.makeupproject.android.networth45

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
    }

    fun Change(view: View) {
        //Change the activity
        val intent = Intent(this@WelcomeActivity, AssetDefinitonActivity::class.java)
        startActivity(intent)
    }
}

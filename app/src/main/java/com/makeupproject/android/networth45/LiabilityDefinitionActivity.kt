package com.makeupproject.android.networth45

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class LiabilityDefinitionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_liability_definition)
    }
    fun Change(view: View){
        //Change the activity
        val intent = Intent(this@LiabilityDefinitionActivity, FinallyActivity::class.java)
        startActivity(intent)
    }
}

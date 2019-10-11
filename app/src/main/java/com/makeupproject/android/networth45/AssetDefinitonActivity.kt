package com.makeupproject.android.networth45

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class AssetDefinitonActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_asset_definiton)
    }
    fun Change(view: View){
        //Change the activity
        val intent = Intent(this@AssetDefinitonActivity, LiabilityDefinitionActivity::class.java)
        startActivity(intent)
    }
}

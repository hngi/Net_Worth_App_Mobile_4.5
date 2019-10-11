package com.makeupproject.android.networth45.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.makeupproject.android.networth45.CalculatorActivity
import com.makeupproject.android.networth45.R

class UserAssetsFragment : Fragment(R.layout.fragment_user_assets) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(activity as CalculatorActivity) {
            this.changeActionBarText("Assets")
        }
    }
}
package com.makeupproject.android.networth45.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.makeupproject.android.networth45.CalculatorActivity
import com.makeupproject.android.networth45.R
import kotlinx.android.synthetic.main.fragment_result_networth.*

class NetworthResultFragment : Fragment(R.layout.fragment_result_networth) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(activity as CalculatorActivity) {
            this.changeActionBarText("Your Net Worth")
        }

        val assets = arguments?.getDouble("TOTAL_ASSETS", 0.0) ?: 0.0
        val liab = arguments?.getDouble("TOTAL_LIABILITIES", 0.0) ?: 0.0

        txtnetworth.text = "Your Net Worth is ₦${assets - liab}"
    }
}
package com.makeupproject.android.networth45.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.makeupproject.android.networth45.CalculatorActivity
import com.makeupproject.android.networth45.R
import com.makeupproject.android.networth45.models.AssetModel
import com.makeupproject.android.networth45.ui.AssetsAdapter
import kotlinx.android.synthetic.main.fragment_user_assets.txtTotal
import kotlinx.android.synthetic.main.fragment_user_liabilities.*
import java.text.DecimalFormat

class UserLiabilitiesFragment : Fragment(R.layout.fragment_user_liabilities), AssetsAdapter.ItemDeletable {

    lateinit var dataset: MutableList<AssetModel>
    lateinit var adapter: AssetsAdapter

    var receivedAssetsTotal = 0.0
    var totalAssets = 0.0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(activity as CalculatorActivity) {
            this.changeActionBarText("Liabilities")
        }

        receivedAssetsTotal = arguments?.getDouble("TOTAL_ASSETS", 0.0) ?: 0.0
        // Set up the list
        dataset = mutableListOf()
        adapter = AssetsAdapter(context, this, dataset)

        recyclerview_liabilities.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerview_liabilities.adapter = adapter

        userliabilitiesFab.setOnClickListener {
            val assetModel =
                AssetModel(editliabilityName.text.toString(), editliabilityValue.text.toString().toDouble())
            dataset.add(assetModel)
            adapter.notifyItemInserted(dataset.size - 1)
            totalAssets += editliabilityValue.text.toString().toDouble()
            editliabilityName.setText("")
            editliabilityValue.setText("")
            updateTotal()
        }

        showworth.setOnClickListener {
            val bundle = Bundle()
            bundle.putDouble("TOTAL_LIABILITIES", totalAssets)
            bundle.putDouble("TOTAL_ASSETS", receivedAssetsTotal)
            totalAssets = 0.0
            dataset.clear()
            Navigation.findNavController(view)
                .navigate(R.id.action_userLiabilitiesFragment_to_networthResultFragment, bundle)
        }
    }

    override fun deleteItem(position: Int) {
        totalAssets -= dataset[position].value
        dataset.removeAt(position)
        adapter.notifyItemRemoved(position)
        updateTotal()
    }

    fun updateTotal() {
        val formatter = DecimalFormat("#,###")
        val formattedNumber = formatter.format(totalAssets)
        txtTotal.text = "LIABILITIES TOTAL: ₦$formattedNumber"
    }
}
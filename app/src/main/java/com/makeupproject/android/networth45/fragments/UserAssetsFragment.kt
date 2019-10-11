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
import kotlinx.android.synthetic.main.fragment_user_assets.*

import java.text.DecimalFormat


class UserAssetsFragment : Fragment(R.layout.fragment_user_assets), AssetsAdapter.ItemDeletable {

    lateinit var dataset: MutableList<AssetModel>
    lateinit var adapter: AssetsAdapter

    var totalAssets = 0.0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(activity as CalculatorActivity) {
            this.changeActionBarText("Assets")
        }

        // Set up the list
        dataset = mutableListOf()
        adapter = AssetsAdapter(context, this, dataset)

        recyclerview_assets.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerview_assets.adapter = adapter

        userAssetsFab.setOnClickListener {
            val assetModel = AssetModel(editAssetName.text.toString(), editAssetValue.text.toString().toDouble())
            dataset.add(assetModel)
            adapter.notifyItemInserted(dataset.size - 1)
            totalAssets += editAssetValue.text.toString().toDouble()
            editAssetName.setText("")
            editAssetValue.setText("")
            updateTotal()
        }

        calcliab.setOnClickListener {
            val bundle = Bundle()
            bundle.putDouble("TOTAL_ASSETS", totalAssets)
            totalAssets = 0.0
            dataset.clear()
            Navigation.findNavController(view)
                .navigate(R.id.action_userAssetsFragment_to_userLiabilitiesFragment, bundle)
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
        txtTotal.text = "ASSETS TOTAL: ₦$formattedNumber"
    }
}
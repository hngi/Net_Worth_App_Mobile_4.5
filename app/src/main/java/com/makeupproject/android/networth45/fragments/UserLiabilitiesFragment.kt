package com.makeupproject.android.networth45.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
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
            //validation of input value
            val liabilityString = editliabilityValue.text.toString().trim()
            val liabilityName = editliabilityName.text.toString().trim()

            if (liabilityName.isEmpty()) {
                // Every Asset needs a name
                showError("Every Liability needs a name")
                return@setOnClickListener
            }

            val assetModel: AssetModel
            var liabilityVal = 0.0
            if (liabilityString.isEmpty()) {
                assetModel = AssetModel(liabilityName, liabilityVal)
            }
            else {
                liabilityVal = liabilityString.toDouble()
                assetModel =
                    AssetModel(liabilityName, liabilityVal)
            }
            //add asset model to data set
            dataset.add(assetModel)
            //notify adapter of change
            adapter.notifyItemInserted(dataset.size - 1)
            //calculate total liabilities
            totalAssets += liabilityVal
            //clear text views
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

    private fun showError(s: String) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show()
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
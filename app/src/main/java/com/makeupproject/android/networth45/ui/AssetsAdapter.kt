package com.makeupproject.android.networth45.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.makeupproject.android.networth45.R
import com.makeupproject.android.networth45.models.AssetModel
import java.text.DecimalFormat

class AssetsAdapter(val context: Context?, val frag: ItemDeletable, var dataset: List<AssetModel>) :
    RecyclerView.Adapter<AssetsAdapter.ViewHolder>() {

    interface ItemDeletable {
        fun deleteItem(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_asset_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val asset = dataset[position]

        holder.assetName.text = asset.name
        val formatter = DecimalFormat("#,###")
        val formattedNumber = formatter.format(asset.value)
        holder.assetValue.text = "₦$formattedNumber"
        holder.deleteIcon.setOnClickListener {
            frag.deleteItem(holder.adapterPosition)
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val assetName = view.findViewById<TextView>(R.id.txtAssetName)
        val assetValue = view.findViewById<TextView>(R.id.txtAssetValue)
        val deleteIcon = view.findViewById<ImageView>(R.id.imgRemoveAsset)
    }

    companion object {
        const val TAG = "MainActivityChatAdapter"
    }
}
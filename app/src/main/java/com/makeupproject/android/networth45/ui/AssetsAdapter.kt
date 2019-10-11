package com.makeupproject.android.networth45.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.makeupproject.android.networth45.R
import com.makeupproject.android.networth45.fragments.UserAssetsFragment
import com.makeupproject.android.networth45.models.AssetModel

class AssetsAdapter(val frag: UserAssetsFragment, var dataset: List<AssetModel>) :
    RecyclerView.Adapter<AssetsAdapter.ViewHolder>() {

    init {
        try {
            frag
        } catch (e: ClassCastException) {
            Log.e(TAG, "Context must implement ItemDeletable")
            throw ClassCastException(e.message)
        }
    }

    interface ItemDeletable {
        fun deleteItem(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(frag.context).inflate(R.layout.layout_asset_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val asset = dataset[position]

        holder.assetName.text = asset.name
        holder.assetValue.text = asset.value.toString()
        holder.deleteIcon.setOnClickListener {
            with(frag as ItemDeletable) {
                this.deleteItem(position)
            }

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
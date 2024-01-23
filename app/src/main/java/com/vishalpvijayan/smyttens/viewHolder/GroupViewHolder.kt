package com.vishalpvijayan.smyttens.viewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vishalpvijayan.smyttens.R
import com.vishalpvijayan.smyttens.adapter.GroupAdapter
import com.vishalpvijayan.smyttens.data.ProductEntity

class GroupViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val batchId: TextView = itemView.findViewById(R.id.groupBatchId)
    private val groupRecyclerView: RecyclerView = itemView.findViewById(R.id.groupRecyclerView)
    fun bind(groupData: List<ProductEntity>, onAddToCartClickListener: (ProductEntity) -> Unit) {
        batchId.text = "Batch ID: ${groupData.firstOrNull()?.batch ?: -1}"
        groupRecyclerView.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
        groupRecyclerView.adapter = GroupAdapter(groupData, onAddToCartClickListener)
    }
}

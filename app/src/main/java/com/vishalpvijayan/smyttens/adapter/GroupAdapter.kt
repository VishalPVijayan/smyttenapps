package com.vishalpvijayan.smyttens.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vishalpvijayan.smyttens.viewHolder.NormalViewHolder
import com.vishalpvijayan.smyttens.R
import com.vishalpvijayan.smyttens.data.ProductEntity

class GroupAdapter(
    private val items: List<ProductEntity>,
    private val onAddToCartClickListener: (ProductEntity) -> Unit
) : RecyclerView.Adapter<NormalViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NormalViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return NormalViewHolder(view, onAddToCartClickListener)
    }
    override fun onBindViewHolder(holder: NormalViewHolder, position: Int) {
        holder.bind(items[position])
    }
    override fun getItemCount(): Int {
        return items.size
    }
}


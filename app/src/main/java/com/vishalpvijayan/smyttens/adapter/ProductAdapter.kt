package com.vishalpvijayan.smyttens.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.vishalpvijayan.smyttens.viewHolder.GroupViewHolder
import com.vishalpvijayan.smyttens.viewHolder.NormalViewHolder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import com.vishalpvijayan.smyttens.R
import com.vishalpvijayan.smyttens.data.ProductEntity
import com.vishalpvijayan.smyttens.database.AppDatabase

class ProductAdapter(
    private val itemsLiveData: MutableLiveData<List<ProductEntity>?>,
    private val database: AppDatabase
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_TYPE_NORMAL = 0
    private val VIEW_TYPE_GROUP = 1

    private val onAddToCartClickListener: (ProductEntity) -> Unit = { product ->
        // Update the cart status and update the Room database
        product.items = !product.items
        GlobalScope.launch(Dispatchers.IO) {
            database.myDao().insertProduct(product)
        }
    }

    private val groupedItems: Map<Int, List<ProductEntity>>
        get() =
            itemsLiveData.value.orEmpty().groupBy { it.batch }

    private val uniqueBatchIds: List<Int>
        get() =
            itemsLiveData.value.orEmpty().distinctBy { it.batch }.map { it.batch }

    override fun getItemViewType(position: Int): Int {
        return if (groupedItems.containsKey(uniqueBatchIds[position])) VIEW_TYPE_GROUP else VIEW_TYPE_NORMAL
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_NORMAL -> {
                val view = inflater.inflate(R.layout.item_product, parent, false)
                NormalViewHolder(view, onAddToCartClickListener)
            }

            VIEW_TYPE_GROUP -> {
                val view = inflater.inflate(R.layout.item_group, parent, false)
                GroupViewHolder(view)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is NormalViewHolder -> {
                val group = groupedItems[uniqueBatchIds[position]]
                if (group != null && group.isNotEmpty()) {
                    holder.bind(group[0])
                } else {
                    // Handle later VISHALL
                }
            }

            is GroupViewHolder -> {
                groupedItems[uniqueBatchIds[position]]?.let {
                    holder.bind(it, onAddToCartClickListener)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return uniqueBatchIds.size
    }


}


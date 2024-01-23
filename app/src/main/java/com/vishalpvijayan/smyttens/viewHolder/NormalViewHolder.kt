package com.vishalpvijayan.smyttens.viewHolder

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.vishalpvijayan.smyttens.R
import com.vishalpvijayan.smyttens.data.ProductEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NormalViewHolder(
    itemView: View,
    private val onAddToCartClickListener: (ProductEntity) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    private val itemImage: ImageView = itemView.findViewById(R.id.normalItemImage)
    private val itemName: TextView = itemView.findViewById(R.id.normalItemName)
    private val itemProductName: TextView = itemView.findViewById(R.id.textViewProductName)
    private val addToCartButton: TextView = itemView.findViewById(R.id.buttonAddToCart)

    fun bind(item: ProductEntity) {
        addToCartButton.post {
            showLoader(true)
            GlobalScope.launch(Dispatchers.Default) {
                delay(2000)

                withContext(Dispatchers.Main) {
                    if (item.items) {
                        addToCartButton.text = "Item Added"
                    } else {
                        addToCartButton.text = "Add to Cart"
                    }

                    // Hide loader after processing
                    showLoader(false)
                }
            }
        }

        itemName.text = item.brand
        itemProductName.text = item.name
        itemImage.load(item.image) {
            placeholder(R.drawable.ic_launcher_background)
            error(R.drawable.ic_launcher_foreground)
            crossfade(true)
        }

        addToCartButton.setOnClickListener {
            onAddToCartClickListener.invoke(item)
        }
    }

    private fun showLoader(show: Boolean) {
        val progressBar: ProgressBar = itemView.findViewById(R.id.progressBar)
        if (show) {
            addToCartButton.isEnabled = false
            progressBar.visibility = View.VISIBLE
        } else {
            addToCartButton.isEnabled = true
            progressBar.visibility = View.GONE
        }
    }
}


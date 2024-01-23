package com.vishalpvijayan.smyttens.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vishalpvijayan.smyttens.R
import com.vishalpvijayan.smyttens.data.ButtonEntity
import com.vishalpvijayan.smyttens.interfaces.OnBtnClickListener


/*class ButtonAdapter(private val items: List<Button>) : ListAdapter<Button, ButtonAdapter.ButtonViewHolder>(ButtonDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ButtonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_button, parent, false)
        return ButtonViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ButtonViewHolder, position: Int) {
        val button = getItem(position)
        holder.bind(button)
//        holder.itemView.setOnClickListener { onItemClick(button) }
    }

    class ButtonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val buttonItem: TextView = itemView.findViewById(R.id.buttonItem)


        fun bind(button: Button) {
            buttonItem.text = button.name
        }
    }

    // DiffCallback to efficiently update the list
    private class ButtonDiffCallback : DiffUtil.ItemCallback<Button>() {
        override fun areItemsTheSame(oldItem: Button, newItem: Button): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Button, newItem: Button): Boolean {
            return oldItem == newItem
        }
    }
}*/


class ButtonAdapter(
    private val itemsLiveData: MutableLiveData<List<ButtonEntity>?>,
    private val clickListener: OnBtnClickListener
) : RecyclerView.Adapter<ButtonAdapter.ButtonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ButtonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_button, parent, false)
        return ButtonViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ButtonViewHolder, position: Int) {

        itemsLiveData.value?.let { buttons ->
            val button = buttons[position]
            holder.bind(button)
            val activityName = button.name
            holder.itemView.setOnClickListener {
                clickListener.onButtonClicked(
                    button,
                    activityName
                )
            }
        }

        /*  val button = items[position]
          holder.bind(button)
          val activityName = items[position].name
          holder.itemView.setOnClickListener { clickListener.onButtonClicked(button,activityName) }*/
    }

    override fun getItemCount(): Int {
        return itemsLiveData.value?.size ?: 0
//        return items.size
    }

    class ButtonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val buttonItem: TextView = itemView.findViewById(R.id.buttonItem)
        fun bind(button: ButtonEntity) {
            buttonItem.text = button.name
        }
    }
}




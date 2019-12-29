package com.akoufatzis.coolweather.presentation.places

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.akoufatzis.coolweather.R
import com.akoufatzis.coolweather.presentation.core.DataBindingAdapter
import com.akoufatzis.coolweather.presentation.core.DataBindingViewHolder

class PlacesAdapter : DataBindingAdapter<Place>(
    DiffCallback()
) {

    var onClick: ((place: Place) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DataBindingViewHolder<Place> {
        val holder = super.onCreateViewHolder(parent, viewType)
        holder.itemView.setOnClickListener {
            val position = holder.adapterPosition
            val place = getItem(position)
            onClick?.invoke(place)
        }
        return holder
    }

    class DiffCallback : DiffUtil.ItemCallback<Place>() {
        // your DiffCallback implementation
        override fun areItemsTheSame(oldItem: Place, newItem: Place): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Place, newItem: Place): Boolean {
            return oldItem == newItem
        }
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_places
    }
}

package com.akoufatzis.coolweather.presentation.weather

import androidx.recyclerview.widget.DiffUtil
import com.akoufatzis.coolweather.R
import com.akoufatzis.coolweather.presentation.core.DataBindingAdapter

class WeatherAdapter : DataBindingAdapter<WeatherItem>(DiffCallback()) {

    class DiffCallback : DiffUtil.ItemCallback<WeatherItem>() {
        // your DiffCallback implementation
        override fun areItemsTheSame(oldItem: WeatherItem, newItem: WeatherItem): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: WeatherItem, newItem: WeatherItem): Boolean {
            return oldItem == newItem
        }
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_weather
    }
}

data class WeatherItem(val name: String, val degrees: String, val iconRes: Int)

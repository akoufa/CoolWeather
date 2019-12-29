package com.akoufatzis.coolweather.presentation.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.akoufatzis.coolweather.presentation.weather.WeatherFragment

class PagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    private var places = listOf<String>()

    override fun getItemCount() = places.size

    override fun createFragment(position: Int): Fragment {
        val place = places[position]
        return WeatherFragment.newInstance(place)
    }

    fun setPlaces(places: List<String>) {
        this.places = places
        notifyDataSetChanged()
    }
}

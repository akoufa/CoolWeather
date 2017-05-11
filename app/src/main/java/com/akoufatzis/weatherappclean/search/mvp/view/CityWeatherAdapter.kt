package com.akoufatzis.weatherappclean.search.mvp.view

import com.akoufatzis.weatherappclean.R
import com.akoufatzis.weatherappclean.custom.BindingRecyclerViewAdapter
import com.akoufatzis.weatherappclean.search.model.CityWeatherModel


/**
 * Created by alexk on 07.05.17.
 */
class CityWeatherAdapter(private val cityWeatherModels: MutableList<CityWeatherModel>) : BindingRecyclerViewAdapter() {

    override fun getObjForPosition(position: Int) = cityWeatherModels[position]

    override fun getLayoutIdForPosition(position: Int) = R.layout.item_city_weather

    override fun getItemCount() = cityWeatherModels.size

    fun addCityWeather(cityWeather: CityWeatherModel) {
        cityWeatherModels.add(0, cityWeather)
        notifyItemInserted(0)
    }
}
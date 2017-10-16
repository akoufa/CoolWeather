package com.akoufatzis.weatherappclean.search

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import com.akoufatzis.weatherappclean.R
import com.akoufatzis.weatherappclean.custom.BindingRecyclerViewAdapter
import com.akoufatzis.weatherappclean.search.model.CityWeatherModel
import com.akoufatzis.weatherappclean.utils.artResource
import com.akoufatzis.weatherappclean.utils.celsiusDegrees


/**
 * Created by alexk on 07.05.17.
 */
class CityWeatherAdapter(val context: Context) : BindingRecyclerViewAdapter() {

    private val cityWeatherModels: MutableList<CityWeather> = arrayListOf()

    class CityWeather(val cityName: String, val temp: String, val icon: Drawable)

    override fun getObjForPosition(position: Int) = cityWeatherModels[position]

    override fun getLayoutIdForPosition(position: Int) = R.layout.item_city_weather

    override fun getItemCount() = cityWeatherModels.size

    fun addCityWeather(cityWeatherModel: CityWeatherModel) {

        val drawable = ContextCompat.getDrawable(context, cityWeatherModel.artResource())
        val cityWeather = CityWeather(cityWeatherModel.city.name, cityWeatherModel.celsiusDegrees(context), drawable)
        cityWeatherModels.add(0, cityWeather)
        notifyItemInserted(0)
    }
}

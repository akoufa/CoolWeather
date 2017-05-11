package com.akoufatzis.weatherappclean.search.model

import com.akoufatzis.weatherappclean.domain.models.City
import com.akoufatzis.weatherappclean.domain.models.Weather

/**
 * The M from the MVP. This is the class that should, if required implement Parceable
 */
data class CityWeatherModel(val weather: Weather, val temp: Double, val city: City)
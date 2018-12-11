package com.akoufatzis.coolweather.data.mappers

import com.akoufatzis.coolweather.data.entities.CityWeatherEntity
import com.akoufatzis.coolweather.domain.weather.City
import com.akoufatzis.coolweather.domain.weather.CityWeather

fun CityWeatherEntity.toCityWeather(): CityWeather {
    val weatherModels = weather.map { w -> w.toWeatherModel() }
    val mainModel = main.toMainModel()
    val city = City(id, name)
    return CityWeather(weatherModels, mainModel, city)
}

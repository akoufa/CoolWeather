package com.akoufatzis.coolweather.data.mappers

import com.akoufatzis.coolweather.data.entities.CityWeatherEntity
import com.akoufatzis.coolweather.domain.weather.City
import com.akoufatzis.coolweather.domain.weather.CityWeather
import com.akoufatzis.coolweather.domain.weather.Temperature
import com.akoufatzis.coolweather.domain.weather.Weather

fun CityWeatherEntity.toCityWeather(): CityWeather {
    val weatherModels = weather.map { Weather( it.id, it.main, it.description, it.icon) }
    val temperature = Temperature(main.temp, main.pressure, main.humidity, main.tempMin, main.tempMax)
    val city = City(id, name)
    return CityWeather(weatherModels, temperature, city)
}

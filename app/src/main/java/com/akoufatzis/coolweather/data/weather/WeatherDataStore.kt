package com.akoufatzis.coolweather.data.weather

import com.akoufatzis.coolweather.BuildConfig
import com.akoufatzis.coolweather.data.openweathermap.OpenWeatherMapApi
import com.akoufatzis.coolweather.data.weather.mappers.toCityWeather
import com.akoufatzis.coolweather.domain.weather.CityWeather
import com.akoufatzis.coolweather.domain.weather.WeatherRepository
import javax.inject.Inject
import javax.inject.Singleton

const val API_KEY = BuildConfig.OPENWEATHERMAP_API_KEY

@Singleton
class WeatherDataStore @Inject constructor(private val api: OpenWeatherMapApi) :
    WeatherRepository {
    override suspend fun fetchCityWeatherData(cityName: String): CityWeather {

        val weatherEntity = api.getWeatherByCityName(
            cityName,
            API_KEY
        )
        return weatherEntity.toCityWeather()
    }
}

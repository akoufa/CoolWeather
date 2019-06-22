package com.akoufatzis.coolweather.openweathermap

import com.akoufatzis.coolweather.domain.Failure
import com.akoufatzis.coolweather.domain.Success
import com.akoufatzis.coolweather.domain.weather.CityWeather
import com.akoufatzis.coolweather.domain.weather.WeatherRepository
import com.akoufatzis.coolweather.openweathermap.mappers.toCityWeather
import javax.inject.Inject
import javax.inject.Singleton

const val API_KEY = BuildConfig.OPENWEATHERMAP_API_KEY

@Singleton
class WeatherDataStore @Inject constructor(private val api: OpenWeatherMapApi) : WeatherRepository {
    override suspend fun fetchCityWeatherData(cityName: String): com.akoufatzis.coolweather.domain.Result<CityWeather> {
        return try {
            val weatherEntity = api.getWeatherByCityName(cityName, API_KEY)
            val weather = weatherEntity.toCityWeather()
            Success(weather)
        } catch (@Suppress("TooGenericExceptionCaught") error: Exception) {
            Failure(error)
        }
    }
}

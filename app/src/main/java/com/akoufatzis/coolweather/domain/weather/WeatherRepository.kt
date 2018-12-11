package com.akoufatzis.coolweather.domain.weather

import com.akoufatzis.coolweather.domain.Result

interface WeatherRepository {

    // Fetches single weather data about a city
    suspend fun fetchCityWeatherData(cityName: String): Result<CityWeather>

}
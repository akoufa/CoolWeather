package com.akoufatzis.coolweather.domain.weather

interface WeatherRepository {

    // Fetches single weather data about a city
    suspend fun fetchCityWeatherData(cityName: String): CityWeather
}

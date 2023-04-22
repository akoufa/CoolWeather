package com.koufatzis.coolweather.ui.navigation

sealed class CoolWeatherDestinations(val route: String) {
    object WeatherDetails : CoolWeatherDestinations("weatherDetails")
}
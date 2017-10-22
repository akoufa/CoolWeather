package com.akoufatzis.weatherappclean.domain.repositories

import com.akoufatzis.weatherappclean.domain.models.CityWeather
import com.akoufatzis.weatherappclean.domain.models.Result
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by alexk on 05.05.17.
 */
interface WeatherRepository {

    // Fetches single weather data about a city
    fun fetchCityWeatherData(cityName: String): Single<Result<CityWeather>>

    // Continuous loads weather data about cities reacting on cityNames observable
    fun loadCityWeatherData(cityNames: Observable<String>): Observable<Result<CityWeather>>
}
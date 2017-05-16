package com.akoufatzis.weatherappclean.domain.repositories

import com.akoufatzis.weatherappclean.domain.models.CityWeather
import com.akoufatzis.weatherappclean.domain.models.Result
import io.reactivex.Observable

/**
 * Created by alexk on 05.05.17.
 */
interface WeatherRepository {

    fun loadCityWeatherData(searchTerm: String): Observable<Result<CityWeather>>
}
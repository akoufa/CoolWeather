package com.akoufatzis.weatherappclean.search.model

import com.akoufatzis.weatherappclean.domain.models.CityWeather
import io.reactivex.ObservableTransformer

/**
 * Created by alexk on 05.05.17.
 */
fun mapToCityWeatherModel() = ObservableTransformer<CityWeather, CityWeatherModel> {
    it.map { (weathers, main, city) ->
        CityWeatherModel(weathers.first(), main.temp, city)
    }
}
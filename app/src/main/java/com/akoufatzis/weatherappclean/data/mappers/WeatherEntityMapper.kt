package com.akoufatzis.weatherappclean.data.mappers

import com.akoufatzis.weatherappclean.data.entities.CityWeatherEntity
import com.akoufatzis.weatherappclean.domain.models.City
import com.akoufatzis.weatherappclean.domain.models.CityWeather
import io.reactivex.ObservableTransformer

/**
 * Created by alexk on 05.05.17.
 */
fun mapToCityWeather() = ObservableTransformer<CityWeatherEntity, CityWeather> {
    it.map { cw ->
        val weatherModelList = cw.weathers.map { w -> w.toWeatherModel() }
        CityWeather(weatherModelList, cw.main.toMainModel(), City(cw.id, cw.name))
    }
}
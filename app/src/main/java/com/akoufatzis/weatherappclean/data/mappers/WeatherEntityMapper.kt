package com.akoufatzis.weatherappclean.data.mappers

import com.akoufatzis.weatherappclean.data.entities.CityWeatherEntity
import com.akoufatzis.weatherappclean.domain.models.*
import io.reactivex.ObservableTransformer

/**
 * Created by alexk on 05.05.17.
 */
fun mapToCityWeather() = ObservableTransformer <Result<CityWeatherEntity>, Result<CityWeather>> {
    it.map { entityResult ->

        if (entityResult.success) {
            val data = entityResult.data!!
            val weatherModelList = data.weathers.map { w -> w.toWeatherModel() }
            val mainModel = data.main.toMainModel()
            val city = City(data.id, data.name)
            val cw = CityWeather(weatherModelList, mainModel, city)
            Success(cw)
        } else if (entityResult.loading) {
            InFlight<CityWeather>()
        } else {
            Failure<CityWeather>(entityResult.error!!)
        }
    }
}
package com.akoufatzis.weatherappclean.data.mappers

import com.akoufatzis.weatherappclean.data.entities.CityWeatherEntity
import com.akoufatzis.weatherappclean.domain.models.*
import io.reactivex.ObservableTransformer

/**
 * Created by alexk on 05.05.17.
 */
fun mapToCityWeather() = ObservableTransformer <Result<CityWeatherEntity>, Result<CityWeather>> {
    it.map { entityResult ->
        when (entityResult) {
            is Success -> {
                val data = entityResult.data!!
                val weatherModelList = data.weathers.map { w -> w.toWeatherModel() }
                val mainModel = data.main.toMainModel()
                val city = City(data.id, data.name)
                val cw = CityWeather(weatherModelList, mainModel, city)
                Success(cw)
            }
            is InFlight -> InFlight<CityWeather>()
            is Failure -> Failure<CityWeather>(entityResult.error!!)
        }
    }
}
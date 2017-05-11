package com.akoufatzis.weatherappclean.data.entities

import com.akoufatzis.weatherappclean.domain.models.Main
import com.akoufatzis.weatherappclean.domain.models.Weather
import com.google.gson.annotations.SerializedName

/**
 * Created by alexk on 11.05.17.
 */
class CityWeatherEntity {
    var id: Long = 0
    var name: String = ""
    @SerializedName("weather")
    var weathers: List<WeatherEntity> = emptyList()
    var main: MainEntity = MainEntity()
}

class MainEntity {
    var temp: Double = 0.toDouble()
    var pressure: Double = 0.toDouble()
    var humidity: Double = 0.toDouble()
    @SerializedName("temp_min")
    var tempMin: Double = 0.toDouble()
    @SerializedName("temp_max")
    var tempMax: Double = 0.toDouble()

    fun toMainModel(): Main {
        return Main(temp, pressure, humidity, tempMin, tempMax)
    }
}

class WeatherEntity {
    var id: Long = 0
    var main: String? = null
    var description: String? = null
    var icon: String? = null

    fun toWeatherModel(): Weather {
        return Weather(id, main, description, icon)
    }
}
package com.akoufatzis.weatherappclean.data.api

import com.akoufatzis.weatherappclean.data.entities.CityWeatherEntity
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by alexk on 05.05.17.
 */
interface RestApi {

    @GET("weather")
    fun getWeatherByCityName(@Query("q") cityName: String, @Query("appid") appId: String): Observable<Response<CityWeatherEntity>>
}
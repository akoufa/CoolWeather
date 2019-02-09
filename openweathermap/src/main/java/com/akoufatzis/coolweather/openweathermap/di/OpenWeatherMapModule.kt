package com.akoufatzis.coolweather.openweathermap.di

import com.akoufatzis.coolweather.domain.weather.WeatherRepository
import com.akoufatzis.coolweather.openweathermap.BuildConfig
import com.akoufatzis.coolweather.openweathermap.OpenWeatherMapApi
import com.akoufatzis.coolweather.openweathermap.WeatherDataStore
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

const val OPENWEATHERMAP_URL = BuildConfig.OPENWEATHERMAP_URL

@Module
class OpenWeatherMapModule {

    companion object {
        val instance = OpenWeatherMapModule()
    }

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(OPENWEATHERMAP_URL)
            .addConverterFactory(
                MoshiConverterFactory.create()
            )
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    @Singleton
    @Provides
    fun provideOpenWeatherMapApi(retrofit: Retrofit): OpenWeatherMapApi {
        return retrofit.create(OpenWeatherMapApi::class.java)
    }

    @Provides
    @Named("OpenWeatherMap")
    fun provideOpenWeatherMapDataStore(openWeatherMapApi: OpenWeatherMapApi): WeatherRepository {
        return WeatherDataStore(openWeatherMapApi)
    }
}
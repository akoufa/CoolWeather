package com.akoufatzis.coolweather.di

import com.akoufatzis.coolweather.BuildConfig
import com.akoufatzis.coolweather.data.openweathermap.OpenWeatherMapApi
import com.akoufatzis.coolweather.data.weather.WeatherDataStore
import com.akoufatzis.coolweather.domain.weather.WeatherRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

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
            .build()
    }

    @Singleton
    @Provides
    fun provideOpenWeatherMapApi(retrofit: Retrofit): OpenWeatherMapApi {
        return retrofit.create(OpenWeatherMapApi::class.java)
    }

    @Provides
    fun provideOpenWeatherMapDataStore(openWeatherMapApi: OpenWeatherMapApi): WeatherRepository {
        return WeatherDataStore(openWeatherMapApi)
    }
}

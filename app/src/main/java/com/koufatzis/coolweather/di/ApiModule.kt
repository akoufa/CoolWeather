package com.koufatzis.coolweather.di

import android.util.Log
import com.koufatzis.coolweather.data.api.weather.OpenWeatherMapApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    private const val OPENWEATHERAPI_URL = "https://api.openweathermap.org/data/2.5/"

    @OptIn(ExperimentalSerializationApi::class)
    @Singleton
    @Provides
    fun provideKtorClient(): HttpClient = HttpClient(CIO) {
        install(Logging) {
            level = LogLevel.INFO
            logger = object : Logger {
                override fun log(message: String) {
                    Log.v("HttpClient", message)
                }
            }
        }

        install(ContentNegotiation) {
            json(Json {
                explicitNulls = false
                ignoreUnknownKeys = true
            })
        }
    }

    @Singleton
    @Provides
    @OpenWeatherMap
    fun provideWeatherApiClient(ktorClient: HttpClient) = ktorClient.config {
        defaultRequest {
            url(OPENWEATHERAPI_URL)
        }
    }

    @Singleton
    @Provides
    fun provideWeatherApi(@OpenWeatherMap ktorClient: HttpClient) = OpenWeatherMapApi(ktorClient)
}
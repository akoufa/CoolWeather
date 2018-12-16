package com.akoufatzis.coolweather.di

import com.akoufatzis.coolweather.BuildConfig
import com.akoufatzis.coolweather.data.OpenWeatherMapApi
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

const val OPENWEATHERMAP_URL = BuildConfig.OPENWEATHERMAP_URL

@Module
class NetworkModule {

    companion object {
        val instance = NetworkModule()
    }

    /*
    @Singleton @Provides
    fun provideOkHttpClient(@NetworkLogger loggingInterceptors: Set<@JvmSuppressWildcards
    Interceptor>):
            OkHttpClient =
        OkHttpClient.Builder().apply {
            loggingInterceptors.forEach {
                addNetworkInterceptor(it)
            }
        }.build()
        */

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
    fun provideBittrexApi(retrofit: Retrofit): OpenWeatherMapApi {
        return retrofit.create(OpenWeatherMapApi::class.java)
    }
}

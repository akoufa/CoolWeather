package com.akoufatzis.weatherappclean.di.modules

import com.akoufatzis.weatherappclean.data.api.RestApi
import com.akoufatzis.weatherappclean.data.stores.CityWeatherDataStore
import com.akoufatzis.weatherappclean.domain.repositories.WeatherRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


/**
 * Created by alexk on 05.05.17.
 */
@Module
class NetworkModule(val baseUrl: String) {

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(loggingInterceptor)
        return builder.build()
    }

    @Provides
    @Singleton
    fun providesGson(): Gson {
        // add custom deserializers here
        return GsonBuilder()
                .create()
    }

    @Provides
    @Singleton
    fun providesRestAdapter(okHttpClient: OkHttpClient, gson: Gson): Retrofit {

        return Retrofit.Builder().baseUrl(baseUrl)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
    }

    @Provides
    @Singleton
    fun providesResApi(retrofit: Retrofit): RestApi {
        return retrofit.create(RestApi::class.java)
    }

    @Provides
    @Singleton
    fun providesGithubRepository(restApi: RestApi): WeatherRepository {
        return CityWeatherDataStore(restApi)
    }
}
package com.akoufatzis.coolweather.presentation.weather

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.akoufatzis.coolweather.domain.weather.Humidity
import com.akoufatzis.coolweather.domain.weather.Temperature
import com.akoufatzis.coolweather.domain.weather.Wind
import com.akoufatzis.coolweather.domain.weather.Pressure
import com.akoufatzis.coolweather.domain.weather.WeatherType
import com.akoufatzis.coolweather.domain.weather.WeatherUseCase
import com.akoufatzis.coolweather.domain.weather.City
import com.akoufatzis.coolweather.domain.weather.CityWeather
import com.akoufatzis.coolweather.domain.weather.Weather
import com.akoufatzis.coolweather.shared.getLiveDataValue
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.stub
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

fun createCityWeather(): CityWeather {
    val city = City("Thessaloniki")
    val temp = Temperature(27.0, 25.0, 30.0)
    val pressure = Pressure(100.0)
    val humidity = Humidity(100.0)
    val wind = Wind(100.0, 123.0)
    val weather =
        Weather(
            type = WeatherType.CLEAR,
            temperature = temp,
            description = null,
            pressure = pressure,
            humidity = humidity,
            wind = wind
        )
    return CityWeather(weather, city)
}

class WeatherViewModelTest {

    // Executes tasks in the Architecture Components in the same thread
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val weatherUseCase: WeatherUseCase = mock()

    private fun withViewModel(): WeatherViewModel {
        return WeatherViewModel(weatherUseCase)
    }

    @UseExperimental(ObsoleteCoroutinesApi::class)
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun showWeatherForCityShouldReturnWeatherViewState() = runBlocking {

        // given
        val viewModel = withViewModel()
        val cityWeather = createCityWeather()
        val result = com.akoufatzis.coolweather.domain.Success(cityWeather)

        weatherUseCase.stub {
            onBlocking {
                invoke(cityWeather.city.name)
            }.doReturn(result)
        }

        val cityWeatherModel = createCityWeather(result.data)

        // when

        viewModel.showWeather(cityWeather.city.name)

        // then

        val loadingViewState = getLiveDataValue(viewModel.viewState)!!
        assertThat(loadingViewState.progress.peek()).isTrue()

        val successViewState = getLiveDataValue(viewModel.viewState)!!
        assertThat(successViewState.data!!).isEqualTo(cityWeatherModel)
    }
}
package com.akoufatzis.coolweather.presentation.weather

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.akoufatzis.coolweather.domain.Success
import com.akoufatzis.coolweather.domain.settings.Celsius
import com.akoufatzis.coolweather.domain.settings.GetTemperatureUnitUseCase
import com.akoufatzis.coolweather.domain.weather.* // ktlint-disable no-wildcard-imports
import com.akoufatzis.coolweather.presentation.settings.TemperatureUnit
import com.akoufatzis.coolweather.shared.getLiveDataValue
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.stub
import com.nhaarman.mockitokotlin2.whenever
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

fun createCityWeather(cityName: String, degrees: Double): CityWeather {
    val city = City(cityName)
    val temp = Temperature(degrees, 25.0, 30.0)
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
    private val getTemperatureUnitUseCase: GetTemperatureUnitUseCase = mock()
    private val weatherMapper: WeatherMapper = mock()

    private fun withViewModel(): WeatherViewModel {
        return WeatherViewModel(weatherUseCase, getTemperatureUnitUseCase, weatherMapper)
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
        val cityName = "Thessaloniki"
        val degrees = 27.0
        val viewModel = withViewModel()
        val tempData = TemperatureData(degrees, TemperatureUnit.CELSIUS)
        val weatherData = WeatherData(cityName, tempData, 100)
        val cityWeather = createCityWeather(cityName, degrees)
        val result = Success(cityWeather)

        // when

        weatherUseCase.stub {
            onBlocking {
                invoke()
            }.doReturn(result)
        }

        whenever(weatherMapper.map(cityWeather, Celsius)).thenReturn(weatherData)
        whenever(getTemperatureUnitUseCase.invoke()).thenReturn(Success(Celsius))
        viewModel.showWeather()

        // then

        val loadingViewState = getLiveDataValue(viewModel.viewState)!!
        assertThat(loadingViewState.progress.peek()).isTrue()

        val successViewState = getLiveDataValue(viewModel.viewState)!!
        assertThat(successViewState.data).isEqualTo(listOf(weatherData))
    }
}
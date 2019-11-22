package com.akoufatzis.coolweather.presentation.weather

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.akoufatzis.coolweather.domain.Success
import com.akoufatzis.coolweather.domain.settings.Celsius
import com.akoufatzis.coolweather.domain.settings.GetTemperatureUnitUseCase
import com.akoufatzis.coolweather.domain.weather.* // ktlint-disable no-wildcard-imports
import com.akoufatzis.coolweather.presentation.settings.TemperatureUnit
import com.akoufatzis.coolweather.shared.CoroutinesTestRule
import com.akoufatzis.coolweather.shared.getLiveDataValue
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.stub
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test

fun createWeather(degrees: Double): Weather {
    val temp = Temperature(degrees)
    val maxTemp = Temperature(degrees)
    val minTemp = Temperature(degrees)
    val pressure = Pressure(100.0)
    val humidity = Humidity(100.0)
    val wind = Wind(100.0, 123.0)
    return Weather(
        type = WeatherType.CLEAR,
        temperature = temp,
        maxTemperature = maxTemp,
        minTemperature = minTemp,
        description = null,
        pressure = pressure,
        humidity = humidity,
        wind = wind
    )
}

class WeatherViewModelTest {

    // Set the main coroutines dispatcher for unit testing.
    // We are setting the above-defined testDispatcher as the Main thread dispatcher.
    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    // Executes tasks in the Architecture Components in the same thread
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val weatherUseCase: WeatherUseCase = mock()
    private val getTemperatureUnitUseCase: GetTemperatureUnitUseCase = mock()
    private val weatherMapper: WeatherMapper = mock()

    private fun withViewModel(): WeatherViewModel {
        return WeatherViewModel(weatherUseCase, getTemperatureUnitUseCase, weatherMapper)
    }

    @Test
    fun showWeatherForCityShouldReturnWeatherViewState() =
        coroutinesTestRule.testDispatcher.runBlockingTest {

            // given
            val placeName = "Thessaloniki"
            val degrees = 27.0
            val viewModel = withViewModel()
            val tempData = TemperatureData(degrees, TemperatureUnit.CELSIUS)
            val weatherData = WeatherData(placeName, tempData, 100)
            val weather = createWeather(degrees)
            val result = Success(weather)

            // when

            weatherUseCase.stub {
                onBlocking {
                    invoke(placeName)
                }.doReturn(result)
            }

            whenever(weatherMapper.map(placeName, weather, Celsius)).thenReturn(weatherData)
            whenever(getTemperatureUnitUseCase.invoke()).thenReturn(Success(Celsius))
            viewModel.showWeather(placeName)

            // then

            val viewState = getLiveDataValue(viewModel.viewState)!!
            assertThat(viewState.progress.peek()).isFalse()
            assertThat(viewState.data).isEqualTo(weatherData)
        }
}
package com.akoufatzis.coolweather.presentation.weather

import org.junit.Rule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.akoufatzis.coolweather.domain.weather.WeatherUseCase
import com.nhaarman.mockitokotlin2.isNotNull
import com.nhaarman.mockitokotlin2.mock
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class WeatherViewModelTest {

    // Executes tasks in the Architecture Components in the same thread
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val weatherUseCase: WeatherUseCase = mock()

    private fun withViewModel(
    ): WeatherViewModel {
        return WeatherViewModel(
            weatherUseCase
        )
    }

    @Test
    fun showWeather() {

        // TODO: Finish this test
        val viewModel = withViewModel()
        assertThat(viewModel, isNotNull())
    }
}
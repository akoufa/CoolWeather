package com.akoufatzis.coolweather.presentation.weather

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akoufatzis.coolweather.core.Event
import com.akoufatzis.coolweather.domain.Failure
import com.akoufatzis.coolweather.domain.Success
import com.akoufatzis.coolweather.domain.settings.Celsius
import com.akoufatzis.coolweather.domain.settings.GetTemperatureUnitUseCase
import com.akoufatzis.coolweather.domain.settings.TemperatureUnit
import com.akoufatzis.coolweather.domain.weather.WeatherUseCase
import kotlinx.coroutines.launch

class WeatherViewModel @ViewModelInject constructor(
    val weatherUseCase: WeatherUseCase,
    val getTemperatureUnitUseCase: GetTemperatureUnitUseCase,
    private val weatherMapper: WeatherMapper
) : ViewModel() {

    private val _viewState = MutableLiveData<WeatherViewState>()
    val viewState: LiveData<WeatherViewState>
        get() = _viewState

    fun showWeather(placeName: String) = viewModelScope.launch {
        showLoading()
        when (val weatherResult = weatherUseCase(placeName)) {
            is Success -> {
                val tempUnit = getTemperatureUnit()
                val weatherData = weatherMapper.map(placeName, weatherResult.data, tempUnit)
                emitUiState(showSuccess = weatherData)
            }
            is Failure -> {
                emitUiState(showError = weatherResult.exception)
            }
        }
    }

    private fun getTemperatureUnit(): TemperatureUnit {
        return when (val result = getTemperatureUnitUseCase()) {
            is Success -> result.data
            is Failure -> Celsius
        }
    }

    private fun showLoading() {
        emitUiState(showProgress = Event(true))
    }

    private fun emitUiState(
        showProgress: Event<Boolean> = Event(false),
        showError: Exception? = null,
        showSuccess: WeatherData? = null
    ) {
        val viewState = WeatherViewState(
            showProgress,
            showError,
            showSuccess
        )
        _viewState.value = viewState
    }
}

package com.akoufatzis.coolweather.presentation.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akoufatzis.coolweather.core.Event
import com.akoufatzis.coolweather.domain.settings.Celsius
import com.akoufatzis.coolweather.domain.settings.GetTemperatureUnitUseCase
import com.akoufatzis.coolweather.domain.settings.TemperatureUnit
import com.akoufatzis.coolweather.domain.weather.WeatherUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeatherViewModel @Inject constructor(
    val weatherUseCase: WeatherUseCase,
    val getTemperatureUnitUseCase: GetTemperatureUnitUseCase,
    private val weatherMapper: WeatherMapper
) : ViewModel() {

    private val _viewState = MutableLiveData<WeatherViewState>()
    val viewState: LiveData<WeatherViewState>
        get() = _viewState


    fun showWeather(cityName: String) = viewModelScope.launch {
        showLoading()
        try {
            val weatherResult = weatherUseCase(cityName)
            val tempUnit = getTemperatureUnit()
            val weatherData = weatherMapper.map(weatherResult, tempUnit)
            emitUiState(showSuccess = weatherData)
        } catch (exception: Exception) {
            emitUiState(showError = exception)
        }
    }

    private fun getTemperatureUnit(): TemperatureUnit {
        return try {
            getTemperatureUnitUseCase()
        } catch (exception: Exception) {
            Celsius
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

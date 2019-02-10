package com.akoufatzis.coolweather.presentation.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.akoufatzis.coolweather.core.Event
import com.akoufatzis.coolweather.domain.Failure
import com.akoufatzis.coolweather.domain.Success
import com.akoufatzis.coolweather.domain.settings.Celsius
import com.akoufatzis.coolweather.domain.settings.GetTemperatureUnitUseCase
import com.akoufatzis.coolweather.domain.settings.TemperatureUnit
import com.akoufatzis.coolweather.domain.weather.WeatherUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class WeatherViewModel @Inject constructor(
    val weatherUseCase: WeatherUseCase,
    val getTemperatureUnitUseCase: GetTemperatureUnitUseCase,
    val weatherMapper: WeatherMapper
) : ViewModel(), CoroutineScope {

    private val job = Job()
    @Suppress("ForbiddenComment")
    // TODO: Inject the coroutineContext
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    private val _viewState = MutableLiveData<WeatherViewState>()
    val viewState: LiveData<WeatherViewState>
        get() = _viewState

    fun showWeather(city: String) = launch {
        showLoading()
        val weatherResult = weatherUseCase(city)
        val tempUnit = getTemperatureUnit()

        when (weatherResult) {
            is Success -> {
                val weatherData = weatherMapper.map(weatherResult.data, tempUnit)
                emitUiState(showSuccess = weatherData)
            }
            is Failure -> emitUiState(showError = weatherResult.exception)
        }
    }

    private fun getTemperatureUnit(): TemperatureUnit {
        val result = getTemperatureUnitUseCase()
        return when (result) {
            is Success -> result.data
            is Failure -> Celsius // Fallback to celsisu
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

    override fun onCleared() {
        job.cancel()
        super.onCleared()
    }
}

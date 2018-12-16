package com.akoufatzis.coolweather.presentation.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.akoufatzis.coolweather.core.Event
import com.akoufatzis.coolweather.domain.Failure
import com.akoufatzis.coolweather.domain.Success
import com.akoufatzis.coolweather.domain.weather.WeatherUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class WeatherViewModel @Inject constructor(
    val weatherUseCase: WeatherUseCase
) : ViewModel(), CoroutineScope {

    private val job = Job()
    @Suppress("ForbiddenComment") // TODO: Inject the coroutineContext
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    private val _viewState = MutableLiveData<WeatherViewState>()
    val viewState: LiveData<WeatherViewState>
        get() = _viewState

    fun showWeather(city: String) = launch {
        showLoading()
        val weatherResult = weatherUseCase(city)
        when (weatherResult) {
            is Success -> {
                val cityWeather = createCityWeather(weatherResult.data)
                emitUiState(showSuccess = cityWeather)
            }
            is Failure -> emitUiState(showError = weatherResult.exception)
        }
    }

    private fun showLoading() {
        emitUiState(showProgress = Event(true))
    }

    private fun emitUiState(
        showProgress: Event<Boolean> = Event(false),
        showError: Exception? = null,
        showSuccess: CityWeather? = null
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

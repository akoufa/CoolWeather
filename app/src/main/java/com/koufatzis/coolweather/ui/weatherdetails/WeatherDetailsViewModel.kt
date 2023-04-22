package com.koufatzis.coolweather.ui.weatherdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.koufatzis.coolweather.data.api.weather.OpenWeatherMapClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherDetailsViewModel @Inject constructor(private val client: OpenWeatherMapClient) :
    ViewModel() {

    private val _uiState = MutableStateFlow(WeatherDetailsState(""))
    val uiState: StateFlow<WeatherDetailsState> = _uiState.asStateFlow()

    fun getWeather() {
        viewModelScope.launch {
            // TODO: Demo code. Change this
            val description = client.getWeather(40.64361, 22.93086).getOrThrow().weathers.first().description
            _uiState.value = WeatherDetailsState(description)
        }
    }
}
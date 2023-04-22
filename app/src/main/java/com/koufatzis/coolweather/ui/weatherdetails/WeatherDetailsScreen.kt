package com.koufatzis.coolweather.ui.weatherdetails

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState

@Composable
fun WeatherDetailsScreen(viewModel: WeatherDetailsViewModel) {
    LaunchedEffect(true) {
        viewModel.getWeather()
    }
    val state = viewModel.uiState.collectAsState()
    Text(text = state.value.description)
}
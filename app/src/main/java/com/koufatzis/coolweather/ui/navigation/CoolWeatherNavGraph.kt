package com.koufatzis.coolweather.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.koufatzis.coolweather.ui.navigation.CoolWeatherDestinations.WeatherDetails
import com.koufatzis.coolweather.ui.weatherdetails.WeatherDetailsScreen

@Composable
fun CoolWeatherNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = WeatherDetails.route) {
        composable(WeatherDetails.route){
            WeatherDetailsScreen(viewModel = hiltViewModel())
        }
    }
}
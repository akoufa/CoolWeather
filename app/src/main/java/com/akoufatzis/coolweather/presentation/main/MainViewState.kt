package com.akoufatzis.coolweather.presentation.main

data class MainViewState(val data: List<String> = emptyList(), val error: Exception? = null)
package com.akoufatzis.coolweather.presentation.places

import com.akoufatzis.coolweather.core.Event

data class PlacesViewState(
    val progress: Event<Boolean>,
    val error: Exception?,
    val data: List<Place>?
)

package com.akoufatzis.coolweather.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.akoufatzis.coolweather.domain.Failure
import com.akoufatzis.coolweather.domain.Success
import com.akoufatzis.coolweather.domain.place.GetPlacesUseCase
import com.akoufatzis.coolweather.domain.place.Place
import com.akoufatzis.coolweather.domain.place.StorePlaceUseCase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getPlacesUseCase: GetPlacesUseCase,
    private val storePlaceUseCase: StorePlaceUseCase
) :
    ViewModel() {

    @UseExperimental(InternalCoroutinesApi::class)
    val viewState = getPlacesUseCase()
        .map { result ->
            when (result) {
                is Success -> MainViewState(result.data.map { it.name })
                is Failure -> MainViewState(error = result.exception)
            }
        }
        .asLiveData(viewModelScope.coroutineContext)

    fun storePlace(place: Place) = viewModelScope.launch {
        storePlaceUseCase(place)
    }
}
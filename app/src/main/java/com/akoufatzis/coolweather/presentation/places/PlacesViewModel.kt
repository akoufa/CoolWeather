package com.akoufatzis.coolweather.presentation.places

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akoufatzis.coolweather.core.Event
import com.akoufatzis.coolweather.domain.Failure
import com.akoufatzis.coolweather.domain.Success
import com.akoufatzis.coolweather.domain.map
import com.akoufatzis.coolweather.domain.place.SearchPlacesUseCase
import com.akoufatzis.coolweather.domain.place.StorePlaceUseCase
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

const val debounceMillis = 300L

class PlacesViewModel @Inject constructor(
    val storePlaceUseCase: StorePlaceUseCase,
    val searchPlacesUseCase: SearchPlacesUseCase
) : ViewModel() {

    private val _viewState = MutableLiveData<PlacesViewState>()
    val viewState: LiveData<PlacesViewState>
        get() = _viewState

    private val places = mutableSetOf<Place>()

    fun searchPlaces(placeName: Flow<String>) = viewModelScope.launch {
        searchPlacesUseCase(placeName.filter { it.length > 2 }.debounce(debounceMillis))
            .flowOn(Dispatchers.IO)
            .map { result ->
                result.map {
                    Place(it.name, it.id, it.country)
                }
            }
            .collect {
                when (it) {
                    is Success -> {
                        places.add(it.data)
                        emitUiState(showSuccess = places.toList())
                    }
                    is Failure -> emitUiState(showError = it.exception)
                }
            }
    }

    fun storePlace(place: Place) = viewModelScope.launch {
        storePlaceUseCase(
            com.akoufatzis.coolweather.domain.place.Place(
                place.name,
                place.id,
                place.country
            )
        )
    }

    private fun emitUiState(
        showProgress: Event<Boolean> = Event(false),
        showError: Exception? = null,
        showSuccess: List<Place>? = null
    ) {
        val viewState = PlacesViewState(
            showProgress,
            showError,
            showSuccess
        )
        _viewState.value = viewState
    }
}

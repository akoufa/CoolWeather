package com.akoufatzis.coolweather.presentation.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akoufatzis.coolweather.domain.Failure
import com.akoufatzis.coolweather.domain.Success
import com.akoufatzis.coolweather.domain.place.Place
import com.akoufatzis.coolweather.domain.place.PlaceUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class OnBoardingViewModel @Inject constructor(
    val placeUseCase: PlaceUseCase
) : ViewModel() {

    private val _viewState = MutableLiveData<Place?>()
    val viewState: LiveData<Place?>
        get() = _viewState

    fun fetchPlace() = viewModelScope.launch {
        when (val result = placeUseCase()) {
            is Success -> _viewState.value = result.data
            is Failure -> _viewState.value = null
        }
    }
}

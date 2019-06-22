package com.akoufatzis.coolweather.presentation.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.akoufatzis.coolweather.domain.Failure
import com.akoufatzis.coolweather.domain.Success
import com.akoufatzis.coolweather.domain.place.Place
import com.akoufatzis.coolweather.domain.place.PlaceUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class OnBoardingViewModel @Inject constructor(
    val placeUseCase: PlaceUseCase
) : ViewModel(), CoroutineScope {
    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    private val _viewState = MutableLiveData<Place?>()
    val viewState: LiveData<Place?>
        get() = _viewState

    fun fetchPlace() = launch {
        when (val result = placeUseCase()) {
            is Success -> _viewState.value = result.data
            is Failure -> _viewState.value = null
        }
    }
}

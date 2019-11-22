package com.akoufatzis.coolweather.domain.place

import javax.inject.Inject

class StorePlaceUseCase @Inject constructor(private val placesRepository: PlacesRepository) {

    suspend operator fun invoke(place: Place) {
        return placesRepository.storePlace(place)
    }
}
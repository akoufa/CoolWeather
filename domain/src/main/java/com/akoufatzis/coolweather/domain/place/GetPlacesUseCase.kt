package com.akoufatzis.coolweather.domain.place

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPlacesUseCase @Inject constructor(private val placesRepository: PlacesRepository) {

    operator fun invoke(): Flow<List<Place>> {
        return placesRepository.observePlaces()
    }
}
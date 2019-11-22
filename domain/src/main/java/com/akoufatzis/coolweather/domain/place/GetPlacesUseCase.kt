package com.akoufatzis.coolweather.domain.place

import com.akoufatzis.coolweather.domain.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPlacesUseCase @Inject constructor(private val placesRepository: PlacesRepository) {

    operator fun invoke(): Flow<Result<List<Place>>> {
        return placesRepository.observePlaces()
    }
}
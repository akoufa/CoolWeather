package com.akoufatzis.coolweather.domain.place

import com.akoufatzis.coolweather.domain.Result
import javax.inject.Inject

class PlaceUseCase @Inject constructor(private val placeRepository: PlaceRepository) {

    suspend operator fun invoke(): Result<Place> {
        return placeRepository.fetchPlace()
    }
}

package com.akoufatzis.coolweather.domain.place

import com.akoufatzis.coolweather.domain.Result

interface PlaceRepository {

    suspend fun fetchPlace(): Result<Place>
}

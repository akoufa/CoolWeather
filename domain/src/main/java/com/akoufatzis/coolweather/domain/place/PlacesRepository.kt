package com.akoufatzis.coolweather.domain.place

import kotlinx.coroutines.flow.Flow


interface PlacesRepository {

    suspend fun storePlace(place: Place)

    fun observePlaces(): Flow<List<Place>>
}

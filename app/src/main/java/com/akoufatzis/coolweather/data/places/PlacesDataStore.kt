package com.akoufatzis.coolweather.data.places

import com.akoufatzis.coolweather.data.database.PlaceDao
import com.akoufatzis.coolweather.data.database.entities.fromPlace
import com.akoufatzis.coolweather.data.database.entities.toPlace
import com.akoufatzis.coolweather.domain.Failure
import com.akoufatzis.coolweather.domain.Result
import com.akoufatzis.coolweather.domain.Success
import com.akoufatzis.coolweather.domain.place.Place
import com.akoufatzis.coolweather.domain.place.PlaceRepository
import javax.inject.Inject
import javax.inject.Singleton

@Suppress("ReturnCount")
@Singleton
class PlacesDataStore @Inject constructor(
    private val locationProvider: LocationProvider,
    private val placeDao: PlaceDao
) : PlaceRepository {

    override suspend fun fetchPlace(): Result<Place> {
        val places = placeDao.loadAll()
        if (places.isNotEmpty()) {
            val placeEntity = places.first()
            return Success(placeEntity.toPlace())
        }
        val localityName = locationProvider.getLocalityName() ?: return Failure(Exception("No place found"))
        val place = Place(localityName)
        placeDao.insert(fromPlace(place))
        return Success(place)
    }
}

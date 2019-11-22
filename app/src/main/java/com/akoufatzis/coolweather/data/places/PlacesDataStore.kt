package com.akoufatzis.coolweather.data.places

import com.akoufatzis.coolweather.data.database.PlaceDao
import com.akoufatzis.coolweather.data.database.entities.fromPlace
import com.akoufatzis.coolweather.data.database.entities.toPlace
import com.akoufatzis.coolweather.domain.Result
import com.akoufatzis.coolweather.domain.Success
import com.akoufatzis.coolweather.domain.place.Place
import com.akoufatzis.coolweather.domain.place.PlacesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlacesDataStore @Inject constructor(
    private val placeDao: PlaceDao
) : PlacesRepository {

    override fun observePlaces(): Flow<Result<List<Place>>> {
        return placeDao.loadAll()
            .map { placeEntities -> Success(placeEntities.map { it.toPlace() }) }
    }

    override suspend fun storePlace(place: Place) {
        val placeEntity = fromPlace(place)
        placeDao.insert(placeEntity)
    }
}

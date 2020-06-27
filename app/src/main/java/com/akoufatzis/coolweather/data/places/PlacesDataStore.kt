package com.akoufatzis.coolweather.data.places

import android.content.Context
import com.akoufatzis.coolweather.data.database.PlaceDao
import com.akoufatzis.coolweather.data.database.entities.fromPlace
import com.akoufatzis.coolweather.data.database.entities.toPlace
import com.akoufatzis.coolweather.domain.Result
import com.akoufatzis.coolweather.domain.Success
import com.akoufatzis.coolweather.domain.place.Place as PlaceModel
import com.akoufatzis.coolweather.domain.place.PlacesRepository
import com.akoufatzis.coolweather.presentation.core.loadJsonFromAsset
import com.akoufatzis.coolweather.presentation.core.toObject
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.*

// We include here the JSON in the assets only for demo purposes. These should be hosted on a server that exposes
// an api to search for cities
const val CITY_JSON = "city-list.json"

@Singleton
class PlacesDataStore @Inject constructor(
    @ApplicationContext private val context: Context,
    private val placeDao: PlaceDao
) : PlacesRepository {

    override fun observePlaces(): Flow<Result<List<PlaceModel>>> {
        return placeDao.loadAll()
            .map { placeEntities -> Success(placeEntities.map { it.toPlace() }) }
    }

    override suspend fun storePlace(place: PlaceModel) {
        val placeEntity = fromPlace(place)
        placeDao.insert(placeEntity)
    }

    override fun searchPlace(placeName: Flow<String>): Flow<Result<PlaceModel>> {
        val places = mutableListOf<Place>()
        // TODO: Improve this. Change to map instead of list
        return placeName
            .onStart {
                val json = context.loadJsonFromAsset(CITY_JSON)
                val parsedPlaces = json?.toObject<Place>()
                if (parsedPlaces != null) {
                    places.addAll(parsedPlaces)
                }
            }
            .flatMapLatest<String, Result<PlaceModel>> {
                val foundPlace: Place? = places.find { place -> place.name == it }
                flowOf(foundPlace).filter { it != null }.map { Success(PlaceModel(it!!.name, it.id, it.country)) }
            }
    }
}

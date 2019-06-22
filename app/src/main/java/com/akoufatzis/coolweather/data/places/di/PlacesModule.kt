package com.akoufatzis.coolweather.data.places.di

import com.akoufatzis.coolweather.data.database.PlaceDao
import com.akoufatzis.coolweather.data.places.LocationProvider
import com.akoufatzis.coolweather.data.places.PlacesDataStore
import com.akoufatzis.coolweather.domain.place.PlaceRepository
import dagger.Module
import dagger.Provides

@Module
class PlacesModule {
    companion object {
        val instance = PlacesModule()
    }

    @Provides
    fun providePlacesRemoteDataStore(locationProvider: LocationProvider, placeDao: PlaceDao): PlaceRepository =
        PlacesDataStore(locationProvider, placeDao)
}

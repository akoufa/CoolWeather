package com.akoufatzis.coolweather.data.places.di

import com.akoufatzis.coolweather.data.database.PlaceDao
import com.akoufatzis.coolweather.data.places.PlacesDataStore
import com.akoufatzis.coolweather.domain.place.PlacesRepository
import dagger.Module
import dagger.Provides

@Module
class PlacesModule {
    companion object {
        val instance = PlacesModule()
    }

    @Provides
    fun providePlacesRemoteDataStore(
        placeDao: PlaceDao
    ): PlacesRepository =
        PlacesDataStore(placeDao)
}

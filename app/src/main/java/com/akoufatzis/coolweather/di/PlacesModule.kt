package com.akoufatzis.coolweather.di

import android.content.Context
import com.akoufatzis.coolweather.data.database.PlaceDao
import com.akoufatzis.coolweather.data.places.PlacesDataStore
import com.akoufatzis.coolweather.domain.place.PlacesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@InstallIn(ApplicationComponent::class)
@Module
object PlacesModule {

    @Provides
    fun providePlacesRemoteDataStore(
        @ApplicationContext context: Context,
        placeDao: PlaceDao
    ): PlacesRepository =
        PlacesDataStore(context, placeDao)
}

package com.akoufatzis.coolweather.di

import android.content.Context
import androidx.room.Room
import com.akoufatzis.coolweather.data.database.AppDatabase
import com.akoufatzis.coolweather.data.database.PlaceDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "coolweather-db").build()
    }

    @Singleton
    @Provides
    fun providePlaceDao(database: AppDatabase): PlaceDao {
        return database.placeDao()
    }
}

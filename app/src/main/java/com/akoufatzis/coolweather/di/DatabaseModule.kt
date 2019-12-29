package com.akoufatzis.coolweather.di

import android.content.Context
import androidx.room.Room
import com.akoufatzis.coolweather.data.database.AppDatabase
import com.akoufatzis.coolweather.data.database.PlaceDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DatabaseModule {

    @Singleton
    @Provides
    @JvmStatic
    fun provideDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "coolweather-db").build()
    }

    @Singleton
    @Provides
    @JvmStatic
    fun providePlaceDao(database: AppDatabase): PlaceDao {
        return database.placeDao()
    }
}

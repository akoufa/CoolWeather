package com.akoufatzis.coolweather.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.akoufatzis.coolweather.data.database.entities.PlaceEntity

@Database(entities = [PlaceEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun placeDao(): PlaceDao
}

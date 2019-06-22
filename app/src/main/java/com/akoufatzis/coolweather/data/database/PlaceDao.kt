package com.akoufatzis.coolweather.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.akoufatzis.coolweather.data.database.entities.PlaceEntity

@Dao
interface PlaceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(place: PlaceEntity)

    @Query("SELECT * FROM places")
    suspend fun loadAll(): List<PlaceEntity>
}

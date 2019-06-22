package com.akoufatzis.coolweather.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.akoufatzis.coolweather.domain.place.Place

@Entity(tableName = "places")
data class PlaceEntity(
    var name: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

fun fromPlace(place: Place) = PlaceEntity(place.name)
fun PlaceEntity.toPlace() = Place(this.name)

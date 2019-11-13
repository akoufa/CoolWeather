package com.akoufatzis.coolweather.data.database.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.akoufatzis.coolweather.domain.place.Place

@Entity(
    tableName = "places", indices = [Index(
        value = ["name", "externalId"],
        unique = true
    )]
)
data class PlaceEntity(
    var name: String,
    var externalId: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

fun fromPlace(place: Place) = PlaceEntity(place.name, place.id)
fun PlaceEntity.toPlace() = Place(this.name, this.externalId)

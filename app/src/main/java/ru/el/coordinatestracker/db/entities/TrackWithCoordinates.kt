package ru.el.coordinatestracker.db.entities

import androidx.room.Embedded
import androidx.room.Relation

data class TrackWithCoordinates (
    @Embedded val track: Tracks,
    @Relation(
        parentColumn = "date",
        entityColumn = "trackDate"
    )
    val coordinates: List<TrackCoordinates>



)
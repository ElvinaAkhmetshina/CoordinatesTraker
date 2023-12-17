package ru.el.coordinatestracker.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TrackCoordinates(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val trackDate: Long,
val coordinates: String

)
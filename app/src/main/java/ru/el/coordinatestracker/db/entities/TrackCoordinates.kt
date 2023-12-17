package ru.el.coordinatestracker.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TrackCoordinates(
    @PrimaryKey(autoGenerate = true)
    val coordId:Int=0,

    val trackId: Int,


val coordinatesX: Double,
    val coordinatesY: Double,
    val coordinateDate: Long

)
package ru.el.coordinatestracker.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "track_details")
data class TrackDetail (
    @PrimaryKey(autoGenerate = true)
    val id: Int =0,
    @ColumnInfo(name = "track_detail")
    val trackDetailID: Int,
    @ColumnInfo
    val coordinates: ArrayList<String>
)

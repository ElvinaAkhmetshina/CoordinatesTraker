package ru.el.coordinatestracker.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Tracks(
    @PrimaryKey(autoGenerate = false)
val date: Long,
    val dateStop: Long,
val distance: Long


)

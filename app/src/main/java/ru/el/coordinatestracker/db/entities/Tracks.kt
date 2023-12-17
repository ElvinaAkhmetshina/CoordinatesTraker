package ru.el.coordinatestracker.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Tracks(
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,

val dateStart: Long,
    val dateEnd: Long,
val distance: Long


)

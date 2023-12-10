package ru.el.coordinatestracker.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

/*@Entity(
    tableName = "all_tracks",
    indices = [Index("id")],
    foreignKeys = [
        ForeignKey(
            entity = TrackDetail::class,
            parentColumns = ["id"],
            childColumns = ["track_detail_id"]
        )
    ]
)

data class TrackDBEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo
    val date: Long,
    @ColumnInfo
    val distance: Long,
    @ColumnInfo(name = "track_detail_id")
    val trackDetailID: Int
)*/
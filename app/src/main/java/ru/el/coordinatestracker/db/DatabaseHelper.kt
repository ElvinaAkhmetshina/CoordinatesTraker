package ru.el.coordinatestracker.db

import kotlinx.coroutines.flow.Flow
import ru.el.coordinatestracker.db.entities.Tracks

interface DatabaseHelper {

    fun getTracks(): Flow<List<Tracks>>

    fun insertAll(tracks: List<Tracks>): Flow<Unit>

}
package ru.el.coordinatestracker.db

import androidx.lifecycle.LiveData
import ru.el.coordinatestracker.db.entities.Tracks

interface DatabaseRepository {
    val readAll: LiveData<List<Tracks>>
    suspend fun create(note: Tracks, onSuccess: () -> Unit)

    suspend fun update(note: Tracks, onSuccess: () -> Unit)

    suspend fun delete(note: Tracks, onSuccess: () -> Unit)

}
package ru.el.coordinatestracker.db

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.el.coordinatestracker.db.entities.Tracks
/*
class DatabaseHelperImpl(private val appDatabase: TracksDatabase) : DatabaseHelper {

    override fun getTracks(): Flow<List<Tracks>> = flow {
        emit(appDatabase.trackDAO.getTracks())
    }

    override fun insertAll(tracks: List<Tracks>): Flow<Unit> = flow {
        appDatabase.trackDAO.insertTrack(tracks)
        emit(Unit)
    }

}
*/

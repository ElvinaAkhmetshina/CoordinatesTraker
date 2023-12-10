package ru.el.coordinatestracker.db
/*
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.el.coordinatestracker.db.entities.Tracks
import ru.el.coordinatestracker.db.model.TrackDBEntity
import ru.el.coordinatestracker.db.model.TrackInfoTuple

class TrackRepository(private val trackDAO: TrackDAO) {
    val allWords: LiveData<List<Tracks> = trackDAO.getTracksWithCoordinates()
    suspend fun insertNewStatisticData(trackDBEntity: TrackDBEntity) {
        withContext(Dispatchers.IO) {
            trackDAO.insertNewData(trackDBEntity)
        }
    }

    suspend fun getAllData(): List<TrackInfoTuple> {
        return withContext(Dispatchers.IO) {
            return@withContext trackDAO.getAllData()
        }
    }

}*/
package ru.el.coordinatestracker.db

import androidx.annotation.WorkerThread
import ru.el.coordinatestracker.db.entities.Tracks
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.toList

import kotlinx.coroutines.launch
import ru.el.coordinatestracker.db.entities.TrackCoordinates

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class TrackRepository(private val trackDao: TrackDAO) {
   // val readAll: LiveData<List<Tracks>>
     //   get() = trackDao.getTracks()

    val allTracks: Flow<List<Tracks>> = trackDao.getTracks()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
/*
    override suspend fun create(note: Note, onSuccess: () -> Unit) {
        noteRoomDao.addNote(note = note)
        onSuccess()
    }*/
 suspend fun create(tracks: Tracks, onSuccess: () -> Unit) {
    trackDao.insertTrack(tracks = tracks)
    onSuccess()
}



    suspend fun create2(track: Tracks, tracks: TrackCoordinates, onSuccess: () -> Unit) {
        trackDao.insertTrack(tracks = track)
        trackDao.insertTrackCoordinates(tracksCoordinates = tracks)
        onSuccess()
    }
    fun readTracks() = trackDao.getTracks()
    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    //val allTracks: Flow<List<Tracks>> = trackDao.getTracks()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    //@Suppress("RedundantSuspendModifier")
    //@WorkerThread
    suspend fun insert(track: Tracks, tracks: TrackCoordinates) {
        trackDao.insertTrack(track)
        trackDao.insertTrackCoordinates(tracks)
    }
}

/*
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
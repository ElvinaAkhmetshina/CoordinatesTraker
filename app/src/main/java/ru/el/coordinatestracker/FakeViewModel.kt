package ru.el.coordinatestracker


import android.app.Application
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import ru.el.coordinatestracker.db.entities.TrackCoordinates
import ru.el.coordinatestracker.db.entities.Tracks


class FakeViewModel(application: Application) : MainViewModel(application) {

    private val fakeTrackList = MutableLiveData<List<Tracks>>()
    private val fakeCoordList = MutableLiveData<List<TrackCoordinates>>()

    init {
        // Статические данные
        fakeTrackList.value = listOf(
            Tracks(dateStart = 1, dateEnd = 2, distance = 3)

            //Др заметки
        )
        fakeCoordList.value = listOf(
            TrackCoordinates(trackId = 1, coordinatesX = 1.1, coordinatesY = 1.1, coordinateDate = 1)
        )
    }

//    override fun addNote(note: Note, onSuccess: () -> Unit) {
//
//        onSuccess()
//    }

//    override fun updateNote(note: Note, onSuccess: () -> Unit) {
//
//    }

//    override fun deleteNote(note: Note, onSuccess: () -> Unit) {
//    }

    override fun readAllTracks(): Flow<List<Tracks>> = fakeTrackList.asFlow()
    override fun readAllCoord(): Flow<List<TrackCoordinates>> = fakeCoordList.asFlow()
}

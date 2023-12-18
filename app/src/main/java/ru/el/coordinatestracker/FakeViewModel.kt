package ru.el.coordinatestracker


import android.app.Application
import androidx.lifecycle.MutableLiveData
import ru.el.coordinatestracker.db.entities.Tracks


class FakeViewModel(application: Application) : MainViewModel(application) {

    private val fakeTrackList = MutableLiveData<List<Tracks>>()

    init {
        // Статические данные
        fakeTrackList.value = listOf(
            Tracks(dateStart = 1, dateEnd = 2, distance = 3),
            Tracks(dateStart = 2, dateEnd = 3, distance = 4)

            //Др заметки
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

    fun readAllTracks() = fakeTrackList
}

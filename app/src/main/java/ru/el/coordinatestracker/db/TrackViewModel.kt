package ru.el.coordinatestracker.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import ru.el.coordinatestracker.db.entities.Tracks
import ru.el.coordinatestracker.utils.REPOSITORY

class TrackViewModel(private val repository: TrackRepository) : ViewModel() {
    val allTracks: LiveData<List<Tracks>> = repository.allTracks.asLiveData()
}
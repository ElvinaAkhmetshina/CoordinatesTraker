package ru.el.coordinatestracker

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.el.coordinatestracker.db.TracksDatabase
import ru.el.coordinatestracker.db.model.Track
import ru.el.coordinatestracker.utils.TYPE_ROOM
import java.lang.IllegalArgumentException

class MainViewModel(application: Application) : AndroidViewModel(application) {
    val context = application

    /*







    fun initDatabase(type: String, onSuccess: () -> Unit)
    {

        Log.d("checkData", "MainViewModel initDatabase with type: $type")
        when(type)
        {

            TYPE_ROOM->{val dao = TracksDatabase.getInstance(context = context).getTrackDao()
                REPOSITORY = RoomRepository(dao)
                onSuccess()}

        }
    }
    */


    /*
    fun addNote(note: Track, onSuccess: () -> Unit)
    {
        viewModelScope.launch(Dispatchers.IO){
           /* REPOSITORY.create(note = note){
                viewModelScope.launch(Dispatchers.Main)
                {
                    onSuccess()
                }
            }*/
        }
    }



    fun updateNote(note: Track, onSuccess: () -> Unit)
    {
        viewModelScope.launch(Dispatchers.IO)
        {
            /*
            REPOSITORY.update(note = note)
            {
                viewModelScope.launch(Dispatchers.Main)
                {
                    onSuccess()
                }
            }*/
        }
    }


    fun deleteNote(note: Track, onSuccess: () -> Unit)
    {
        viewModelScope.launch(Dispatchers.IO)
        {
            /*REPOSITORY.delete(note = note)
            {
                viewModelScope.launch(Dispatchers.Main)
                {
                    onSuccess()
                }
            }*/
        }
    }*/

    //fun readAllNotes() = REPOSITORY.readAll
    //////comment




    private lateinit var applicationContext: Context

    fun init(context: Context) {
        applicationContext = context
    }

    private val appDatabase: TracksDatabase by lazy {
        Room.databaseBuilder(applicationContext, TracksDatabase::class.java, "database.db")
            .createFromAsset("room_article.db")
            .build()
    }
/*
    fun insertNewDataInDatabase(mistakes: Long, points: Long) {
        viewModelScope.launch {
            val newStatistic = Statistic(currentResult, currentDifficultyLevel, mistakes, points)
            statisticRepository.insertNewStatisticData(newStatistic.toStatisticDbEntity())
        }
    }*/
}

class MainViewModelFactory(private val application: Application) : ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(application = application) as T
        }
        throw IllegalArgumentException("Unknowqn ViewModel class")
    }
}
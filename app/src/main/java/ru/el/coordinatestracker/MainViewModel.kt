package ru.el.coordinatestracker

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.el.coordinatestracker.model.Track
import java.lang.IllegalArgumentException

class MainViewModel(application: Application) : AndroidViewModel(application) {
    val context = application

    fun initDatabase(type: String, onSuccess: () -> Unit)
    {

        Log.d("checkData", "MainViewModel initDatabase with type: $type")
        when(type)
        {
            /*
            TYPE_ROOM->{val dao = AppRoomDatabase.getInstance(context = context).getRoomDao()
                REPOSITORY = RoomRepository(dao)
                onSuccess()}*/

        }
    }


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
    }

    //fun readAllNotes() = REPOSITORY.readAll
    //////comment
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
package ru.el.coordinatestracker

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.el.coordinatestracker.db.TrackRepository
import ru.el.coordinatestracker.db.TracksDatabase
import ru.el.coordinatestracker.db.entities.Tracks

import java.lang.IllegalArgumentException

class MainViewModel(application: Application) : AndroidViewModel(application) {
    //worked!!!!
    val context = application

    //val dao = TracksDatabase.getInstance(context).trackDAO

    // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    /*
    val allTracks: LiveData<List<Tracks>> = repository.allTracks.asLiveData()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(track: Tracks) = viewModelScope.launch {
        repository.insert(track)
    }*/




//wored

     suspend fun initDatabase(type: String, onSuccess: () -> Unit)
     {
 /*
         Log.d("checkData", "MainViewModel initDatabase with type: $type")
         when(type)
         {

             TYPE_ROOM->{val dao = TracksDatabase.getInstance(context = context).getTrackDao()
                 REPOSITORY = RoomRepository(dao)
                 onSuccess()}

         }




        val dao = TracksDatabase.getInstance(context).TrackDAO


        val schools = listOf(
            Tracks(1111, 1111)
        )


        val students = listOf(
            TrackCoordinates(1111, 2222)
        )



        /*
        lifecycleScope.launch {

            schools.forEach { dao.insertTrack(it) }

            students.forEach { dao.insertTrackCoordinates(it) }
        }*/
        */

    }
    /*
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




    //private lateinit var applicationContext: Context


    /*
        fun init(context: Context) {
            applicationContext = context
        }

        private val appDatabase: TracksDatabase by lazy {
            Room.databaseBuilder(applicationContext, TracksDatabase::class.java, "database.db")
                .createFromAsset("room_article.db")
                .build()
        }

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
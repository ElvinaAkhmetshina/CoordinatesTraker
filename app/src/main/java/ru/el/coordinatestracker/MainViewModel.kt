package ru.el.coordinatestracker
import android.Manifest
import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Looper
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.el.coordinatestracker.db.TrackRepository
import ru.el.coordinatestracker.db.TracksDatabase
import ru.el.coordinatestracker.db.entities.Tracks
import ru.el.coordinatestracker.locating.Locator
import ru.el.coordinatestracker.locating.Locator.locationCallback
import ru.el.coordinatestracker.locating.Locator.locationRequest
import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.location.LocationManager
import android.location.LocationRequest
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import ru.el.coordinatestracker.db.entities.TrackCoordinates
import ru.el.coordinatestracker.utils.REPOSITORY
import java.lang.IllegalArgumentException

open class MainViewModel(application: Application) : AndroidViewModel(application) {
    //worked!!!!
    val context = application

    //код с лекции
    var showRequestDialog: Boolean by mutableStateOf(true)
    var updJob: Job? = null

    private val fusedLocationClient = LocationServices
        .getFusedLocationProviderClient(application.applicationContext)

    var requestLocationUpdates by mutableStateOf(true)

    private val _location: MutableStateFlow<Location?> = MutableStateFlow(null)
    val location: StateFlow<Location?> = _location
    //fun readAll() = REPOSITORY.readAll
    val db = TracksDatabase.getDao(
        getApplication<Application>().applicationContext
    )
    //val tracks: LiveData<List<Tracks>>
      //  get() = db.getTracks()
    @SuppressLint("MissingPermission")
    fun startLocationUpdates() {
        if (isPermissionsGranted(
                ACCESS_FINE_LOCATION,
                ACCESS_COARSE_LOCATION, context = getApplication<Application>().applicationContext)) {
            fusedLocationClient.lastLocation.addOnCompleteListener {
                viewModelScope.launch {
                    _location.emit(it.result)
                    fusedLocationClient.requestLocationUpdates(
                        locationRequest,
                        locationCallback,
                        Looper.getMainLooper()
                    )
                }
            }

            updJob = viewModelScope.launch {
                Locator.location.collect {
                    _location.emit(it)
                }
            }

        }
    }

    fun stopLocationUpdates() {
        updJob?.cancel()
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    fun isPermissionsGranted(vararg permissions: String, context: Context) =
        permissions.fold(true) { acc, perm ->
            acc && context.checkSelfPermission(perm) == PackageManager.PERMISSION_GRANTED
        }

/*
    @Composable
    fun StartTracking(viewModel: MainViewModel, isTracking: Boolean, received_tracks: MutableList<String>): MutableList<String> {
        val loc by viewModel.location.collectAsState()
        val locStr = loc?.let { "Lat: ${it.latitude} Lon: ${it.longitude}" } ?: "Unknown location"
        //val isTracking = true
        //var received_tracks: MutableList<String> = mutableListOf()
        while (isTracking)
        {
            received_tracks.add(locStr)
        }

       return received_tracks
    }*/



/*
    fun insertTrack(note: Tracks, onSuccess: () -> Unit)
    {
        viewModelScope.launch(Dispatchers.IO){
            REPOSITORY.create(tracks = note){
                viewModelScope.launch(Dispatchers.Main)
                {
                    onSuccess()
                }
            }
        }
    }*/
    fun insertTrackCoordinates(tracks: TrackCoordinates)
    {
        viewModelScope.launch {
        TracksDatabase.getDao(context).apply {

            insertTrackCoordinates(tracks)
            //val tracks = getTracks()
            //Log.i("TRACKS", getTracks().joinToString())
        }
    }

    }

    fun insertAll(tracks: Tracks)
    {
        viewModelScope.launch {
            TracksDatabase.getDao(context).apply {
                //insertTrackCoordinates(track)
                var test = insertTrack(tracks)
                //println(test)
                //val tracks = getTracks()
                //Log.i("TRACKS", getTracks().joinToString())
            }
        }

    }
    fun UpdateDistance(trackId:Int, Distance: Double)
    {
        viewModelScope.launch {
            TracksDatabase.getDao(context).apply {
                updateDistance(trackId, Distance)
            }
        }

    }

    fun UpdateDateEnd(trackId:Int, dateEnd: Long)
    {
        viewModelScope.launch {
            TracksDatabase.getDao(context).apply {
                updateDateEnd(trackId, dateEnd)
            }
        }

    }
/*
    fun insertTrack(tracks: Tracks): Int
    {
        var id: Int
        id = 0
        viewModelScope.launch {
            TracksDatabase.getDao(context).apply {
                //insertTrackCoordinates(track)
                id = insertTracks(tracks)
                //println(test)
                //val tracks = getTracks()
                //Log.i("TRACKS", getTracks().joinToString())
            }
        }
        return id
    }
*/


/*
    fun getCoordinates(date: Long)
    {
        viewModelScope.launch {
            TracksDatabase.getDao(context).apply {
                getTrackWithCoordinates(date.toString())
                //val tracks = getTracks()
                //Log.i("TRACKS")
            }
        }
    }*/
    /*
    fun loop(received_tracks: MutableList<String>,received_dateStart: MutableList<Long>,received_dateEnd: MutableList<Long>, locStr: String, isTracking: Boolean) {

        viewModelScope.launch {
            delay(10000)
            viewModelScope.launch {
                if (isTracking) {
                    //var dateStart= System.currentTimeMillis() / 1000
                    received_dateStart.add(System.currentTimeMillis() / 1000)
                    received_tracks.add(locStr)
                    //var dateEnd= System.currentTimeMillis() / 1000
                    received_dateEnd.add(System.currentTimeMillis())
                    loop(received_tracks, received_dateStart, received_dateEnd, locStr, isTracking)
                }

            }
        }*/


    }











    //fun getAllTracks() {
      //  var tracks: LiveData<List<Tracks>> = db.getTracks()
   // }
    //fun readTracks() = REPOSITORY.readAll
/*
    fun initDatabase(onSuccess: () -> Unit)
    {
        val dao = TracksDatabase.getInstance(context = context).getDao()
        REPOSITORY = TrackRepository(dao)
        onSuccess()

    }*/




    //my code!!


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

     //suspend fun initDatabase(type: String, onSuccess: () -> Unit)
     //{
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

   // }
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





//my working code

/*
class MainViewModelFactory(private val application: Application) : ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(application = application) as T
        }
        throw IllegalArgumentException("Unknowqn ViewModel class")
    }
}*/
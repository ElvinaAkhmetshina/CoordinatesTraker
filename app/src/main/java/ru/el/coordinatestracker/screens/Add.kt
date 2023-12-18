package ru.el.coordinatestracker.screens

import android.app.Application
import android.location.Location
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ru.el.coordinatestracker.FakeViewModel
import ru.el.coordinatestracker.MainViewModel
import ru.el.coordinatestracker.db.entities.TrackCoordinates
import ru.el.coordinatestracker.navigation.NavigationPath
import ru.el.coordinatestracker.ui.theme.CoordinatesTrackerTheme

//import ru.el.coordinatestracker.MainViewModelFactory

import ru.el.coordinatestracker.utils.Constants.Keys.ADD_NOTE





var TrackCoordinates: MutableList<TrackCoordinates> = mutableListOf()
@Composable
fun AddScreen(navController: NavHostController, viewModel: MainViewModel, trackId: String?) {
    //println(trackId)
    // var title by remember{ mutableStateOf("") }
    //var subtitle by remember{ mutableStateOf("") }
    //var isTracking = true
    // var priority by remember{ mutableStateOf("") }
    //var isButtonEnabledStart by remember { mutableStateOf(true) }
    //var isButtonEnabled by remember { mutableStateOf(false) }
    //println(trackId)
    var dateStart = System.currentTimeMillis() / 1000
    val loc by viewModel.location.collectAsState()
    val locX = loc?.longitude
    val locY = loc?.latitude



    if (trackId != null && locX != null && locY!=null) {
        var newTrackCoordinates = TrackCoordinates(trackId = trackId.toInt(), coordinatesX = locX, coordinatesY = locY, coordinateDate = dateStart)
        viewModel.insertTrackCoordinates(newTrackCoordinates)
        TrackCoordinates.add(newTrackCoordinates)
    }


    //val locStr = loc?.let { "Lat: ${it.latitude} Lon: ${it.longitude}" } ?: "Unknown location"

    //var received_tracks: MutableList<String> = mutableListOf()
    //viewModel.insertAll(Tracks(received_dateStart[0], received_dateEnd[0], 3))
    //viewModel.insertTrackCoordinates(TrackCoordinates(received_dateStart[0], received_tracks[0]))
    //received_tracks.add(locStr)
    //println(received_tracks)
    //println("test")
    var rt = "ttt"

    /*var dateEnd = System.currentTimeMillis() / 1000
    var received_tracks: MutableList<String> = mutableListOf()
    //received_tracks.add(locStr)
    var received_dateStart: MutableList<Long> = mutableListOf()
    //received_dateStart.add(dateStart)
    var received_dateEnd: MutableList<Long> = mutableListOf()
    //received_dateEnd.add(dateEnd)


    viewModel.loop(received_tracks,received_dateStart,received_dateEnd, locStr, isTracking)*/

    ////работало
//viewModel.insertAll(Tracks(1,2,3),TrackCoordinates(1,1,"11"))


    //viewModel.insertTrackCoordinates(TrackCoordinates(dateStart, locStr))




    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {

        Text(
            text = ADD_NOTE,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 8.dp)
        )/*
        OutlinedTextField(value = title, onValueChange = {
            title = it
            isButtonEnabled = title.isNotEmpty() && subtitle.isNotEmpty()
        }, label = { Text(text = "Введите заголовок заметки") }, isError = title.isEmpty())
        OutlinedTextField(value = subtitle, onValueChange = {
            subtitle = it
            isButtonEnabled = title.isNotEmpty() && subtitle.isNotEmpty()
        }, label = { Text(text = "Введите текст заметки") }, isError = subtitle.isEmpty())
*/

        Column(Modifier.selectableGroup())
        {

        }



        Button(
            modifier = Modifier.padding(top = 16.dp),

            onClick = {
                var distance: Double
                distance = 0.0

                for (i in 0 until TrackCoordinates.size-1) {

                        var newDistance = GetDistance(
                            TrackCoordinates[i].coordinatesX,
                            TrackCoordinates[i].coordinatesY,
                            TrackCoordinates[i + 1].coordinatesX,
                            TrackCoordinates[i + 1].coordinatesY
                        )
                        distance += newDistance

                }

if (trackId !=null){
viewModel.UpdateDistance(trackId.toInt(),distance)}
                navController.navigate(NavigationPath.Start.route)
            //isTracking = !isTracking
                //Start()
                //println(received_tracks)
                //received_tracks.add(locStr)
                //received_dateStart.add(6)
                //received_dateEnd.add(6)
                //if (isTracking) {


                    //viewModel.loop(received_tracks,received_dateStart, received_dateEnd, locStr, isTracking)


                    //loop
                //}


                //navController.navigate(NavigationPath.Start.route)
                /*
                viewModel.addNote(
                    note = Track(

                        title = title,
                        subtitle = subtitle,
                        date = System.currentTimeMillis()/1000,
                        priority = priority.toInt()
                    )
                ) {
                    navController.navigate(NavigationPath.List.route)
                }*/
            }

        )
        {
            Text(text = "Остановить запись", fontSize = 24.sp)
        }


        //Text(text = rt, fontSize = 24.sp)
    }
}



@Composable
fun Start(viewModel: MainViewModel)
{
    val loc by viewModel.location.collectAsState()
    val locStr = loc?.let { "Lat: ${it.latitude} Lon: ${it.longitude}" } ?: "Unknown location"
}

fun Stop()
{
    println("Start")
}


fun GetDistance(locationAX: Double,locationAY: Double, locationBX: Double, locationBY: Double): Double
{
    val startPoint = Location("locationA")
    startPoint.setLatitude(locationAY)
    startPoint.setLongitude(locationAX)

    val endPoint = Location("locationA")
    endPoint.setLatitude(locationBY)
    endPoint.setLongitude(locationBX)

    val distance: Double = startPoint.distanceTo(endPoint).toDouble()
    return distance
}


//}


/*
text = DateTimeFormatter
.ofPattern("yyyy-MM-dd HH:mm")
.withZone( ZoneId.of("Europe/Moscow"))
.format(Instant.ofEpochSecond(track.date)),
fontSize = 32.sp,
fontWeight = FontWeight.Bold
*/
@Composable
@Preview(showBackground = true)
fun prevAddScreen() {
    CoordinatesTrackerTheme {
        val context =  LocalContext.current
        val fakeViewModel = FakeViewModel(application = Application())
        AddScreen(navController = rememberNavController(), viewModel = fakeViewModel, trackId = "1")
    }

}


/*
@Preview(showBackground = true)
@Composable
fun prevAddScreen(){
    CoordinatesTrackerTheme {
        val context = LocalContext.current
        val mViewModel: MainViewModel = viewModel(factory = MainViewModelFactory(context.applicationContext as Application))

        AddScreen(navController = rememberNavController(), viewModel = mViewModel)
    }
}*/
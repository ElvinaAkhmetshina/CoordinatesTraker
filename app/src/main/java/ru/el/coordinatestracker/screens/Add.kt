package ru.el.coordinatestracker.screens

import android.annotation.SuppressLint
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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.launch
import ru.el.coordinatestracker.MainViewModel
import ru.el.coordinatestracker.db.TracksDatabase
import ru.el.coordinatestracker.db.entities.TrackCoordinates
import ru.el.coordinatestracker.db.entities.Tracks

//import ru.el.coordinatestracker.MainViewModelFactory

import ru.el.coordinatestracker.navigation.NavigationPath
import ru.el.coordinatestracker.utils.Constants
import ru.el.coordinatestracker.utils.Constants.Keys.ADD_NOTE






@Composable
fun AddScreen(navController: NavHostController, viewModel: MainViewModel){
   // var title by remember{ mutableStateOf("") }
    //var subtitle by remember{ mutableStateOf("") }
    var isTracking by remember{mutableStateOf(false)}
   // var priority by remember{ mutableStateOf("") }
    var isButtonEnabledStart by remember{ mutableStateOf(true) }
    var isButtonEnabled by remember{ mutableStateOf(false) }
    val loc by viewModel.location.collectAsState()
    val locStr = loc?.let { "Lat: ${it.latitude} Lon: ${it.longitude}" } ?: "Unknown location"
    var rt = "ttt"

    var received_tracks: MutableList<String> = mutableListOf()
    received_tracks.add(locStr)

    //viewModel.insertTrackCoordinates(Tracks(1,2,3),TrackCoordinates(1,1,"11")){}




    ////работало
//viewModel.insertAll(Tracks(1,2,3),TrackCoordinates(1,1,"11"))



    //вроде работало
    //while (received_tracks.last() != locStr)
      //  received_tracks.add(locStr)
fun StartTracking(received_tracks: MutableList<String>, isTracking: Boolean) {
    val isTracking = !isTracking

    while (isTracking)
    {
        received_tracks.add(locStr)
    }
    //var received_tracks: MutableList<String> = mutableListOf()

}


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





        //stop
        Button(
            modifier = Modifier.padding(top = 16.dp),
            enabled = isButtonEnabledStart,
            onClick = {isTracking = !isTracking
                if (isTracking) {
                    received_tracks.add(locStr)
                    viewModel.insertAll(Tracks(2,3,3),TrackCoordinates(1,2,"22"))
                }



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
            Text(text = "Start|Stop", fontSize = 24.sp)
        }
        //back
        Button(

            onClick = { //val tracks = receivedTracks.toString()
                rt = received_tracks.toString() }
        )
        {

            Text(text = "save", fontSize = 24.sp)

        }
        Text(text = rt, fontSize = 24.sp)
    }







//}
}

/*
text = DateTimeFormatter
.ofPattern("yyyy-MM-dd HH:mm")
.withZone( ZoneId.of("Europe/Moscow"))
.format(Instant.ofEpochSecond(track.date)),
fontSize = 32.sp,
fontWeight = FontWeight.Bold
*/



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
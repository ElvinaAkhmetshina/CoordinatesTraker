package ru.el.coordinatestracker.screens

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import ru.el.coordinatestracker.MainViewModel
import ru.el.coordinatestracker.db.entities.TrackCoordinates
import ru.el.coordinatestracker.db.entities.Tracks
import ru.el.coordinatestracker.utils.Constants

@Composable
fun SaveScreen(navController: NavHostController, viewModel: MainViewModel) {
    // var title by remember{ mutableStateOf("") }
    //var subtitle by remember{ mutableStateOf("") }
    var isTracking by remember { mutableStateOf(false) }
    // var priority by remember{ mutableStateOf("") }
    var isButtonEnabledStart by remember { mutableStateOf(true) }
    var isButtonEnabled by remember { mutableStateOf(false) }
    val loc by viewModel.location.collectAsState()
    val locStr = loc?.let { "Lat: ${it.latitude} Lon: ${it.longitude}" } ?: "Unknown location"
    var rt = "ttt"
    var dateStart = System.currentTimeMillis() / 1000
    var dateEnd = System.currentTimeMillis() / 1000
    var received_tracks: MutableList<String> = mutableListOf()
    //received_tracks.add(locStr)
    var received_dateStart: MutableList<Long> = mutableListOf()
    //received_dateStart.add(dateStart)
    var received_dateEnd: MutableList<Long> = mutableListOf()
    //received_dateEnd.add(dateEnd)

    //viewModel.insertTrackCoordinates(Tracks(1,2,3),TrackCoordinates(1,1,"11")){}


    ////работало
//viewModel.insertAll(Tracks(1,2,3),TrackCoordinates(1,1,"11"))


    //вроде работало
    //while (received_tracks.last() != locStr)
    //  received_tracks.add(locStr)
    //viewModel.insertAll(Tracks(received_dateStart[0], received_dateEnd[0], 3))
    //viewModel.insertTrackCoordinates(TrackCoordinates(received_dateStart[0], received_tracks[0]))


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {

        Text(
            text = Constants.Keys.ADD_NOTE,
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
            onClick = {//isTracking = !isTracking
                isTracking = true
                received_tracks.add(locStr)
                received_dateStart.add(6)
                received_dateEnd.add(6)
                if (isTracking) {


                    //viewModel.loop(received_tracks,received_dateStart, received_dateEnd, locStr, isTracking)


                    //loop
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
            Text(text = "Stop", fontSize = 24.sp)
        }
        //back
        Button(

            onClick = {
                isTracking = false
                //viewModel.insertTrackAndCoordinates(Tracks(5,5,5), TrackCoordinates(5,"555"))
                //var i = 0
                //viewModel.insertTrackAndCoordinates(Tracks(received_dateStart[0],received_dateEnd[0],3), TrackCoordinates(received_dateStart[0],received_tracks[0]))
                var size = received_dateEnd.size
                //for (i in 0..1) {
                //  var date = received_dateStart[i]
                //viewModel.insertTrackAndCoordinates(Tracks(date,date,3), TrackCoordinates(date,received_tracks[i]))
                //val tracks = receivedTracks.toString()
                //}
                //navController.navigate(NavigationPath.List.route)
            }
        )
        {

            Text(text = "save", fontSize = 24.sp)

        }
        Text(text = rt, fontSize = 24.sp)
    }
}
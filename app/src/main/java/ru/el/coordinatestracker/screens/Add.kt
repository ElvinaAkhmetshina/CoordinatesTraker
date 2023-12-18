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
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ru.el.coordinatestracker.FakeViewModel
import ru.el.coordinatestracker.MainViewModel
import ru.el.coordinatestracker.db.entities.TrackCoordinates
import ru.el.coordinatestracker.db.entities.Tracks
import ru.el.coordinatestracker.navigation.NavigationPath
import ru.el.coordinatestracker.ui.theme.CoordinatesTrackerTheme

//import ru.el.coordinatestracker.MainViewModelFactory

import ru.el.coordinatestracker.utils.Constants.Keys.ADD_NOTE





var TrackCoordinates: MutableList<TrackCoordinates> = mutableListOf()
@Composable
fun AddScreen(navController: NavHostController, viewModel: MainViewModel, trackId: String?) {

    var dateStart = System.currentTimeMillis() / 1000
    val loc by viewModel.location.collectAsState()
    val locX = loc?.longitude
    val locY = loc?.latitude



    if (trackId != null && locX != null && locY!=null) {
        var newTrackCoordinates = TrackCoordinates(trackId = trackId.toInt(), coordinatesX = locX, coordinatesY = locY, coordinateDate = dateStart)
        viewModel.insertTrackCoordinates(newTrackCoordinates)
        TrackCoordinates.add(newTrackCoordinates)
    }



    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {

        Text(
            text = "Идет сбор координат!",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 8.dp)
        )


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
                var dateEnd = System.currentTimeMillis() / 1000
    if (trackId !=null){
viewModel.UpdateDistance(trackId.toInt(),distance)
        viewModel.UpdateDateEnd(trackId.toInt(),dateEnd)
        }
                navController.navigate(NavigationPath.Start.route)


            }

        )
        {
            Text(text = "Остановить запись", fontSize = 24.sp)
        }



    }
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



@Composable
@Preview(showBackground = true)
fun prevAddScreen() {
    CoordinatesTrackerTheme {

        val context =  LocalContext.current
        val fakeViewModel = FakeViewModel(application = Application())
        val viewModel = MainViewModel(Application())
        AddScreen(navController = rememberNavController(), viewModel = viewModel, trackId = "1")
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
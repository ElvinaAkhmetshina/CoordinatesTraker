package ru.el.coordinatestracker.screens

import android.app.Application
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import ru.el.coordinatestracker.MainViewModel
import ru.el.coordinatestracker.db.entities.Tracks
//import ru.el.coordinatestracker.MainViewModelFactory

import ru.el.coordinatestracker.navigation.NavigationPath
import ru.el.coordinatestracker.ui.theme.CoordinatesTrackerTheme
import ru.el.coordinatestracker.utils.Constants

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TrackScreen(navController: NavHostController, viewModel: MainViewModel){
    var isButtonEnabledStart by remember{ mutableStateOf(true) }
    val loc by viewModel.location.collectAsState()
    val locStr = loc?.let { "Lat: ${it.latitude} Lon: ${it.longitude}" } ?: "Unknown location"
    var received_tracks: MutableList<String> = mutableListOf()
    received_tracks.add(locStr)
    while (received_tracks.last() != locStr)
        received_tracks.add(locStr)
    //viewModel.StartNewTracking(viewModel = viewModel, isTracking = true, received_tracks = received_tracks)


  Text(text = locStr)
    println(received_tracks.toString())
    Button(
        modifier = Modifier.padding(top = 16.dp),
        enabled = isButtonEnabledStart,
        onClick = {//isTracking=false
            //rt = received_tracks.toString()

            println(received_tracks.toString())
            navController.navigate(NavigationPath.Start.route)
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
        Text(text = "Остановить сбор координат", fontSize = 24.sp)
    }
///add to database!!!!






    //val tracks = viewModel.readAll().collectAsState(listOf()).value
    //val track = tracks.firstOrNull { it.date == date?.toLong() } ?: Tracks(
      // date = 0,
       // distance = 0
    //)
    //val track = "track"
    //val bottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    //val coroutineScope = rememberCoroutineScope()
    //var title by remember { mutableStateOf(Constants.Keys.EMPTY) }
    //var subtitle by remember { mutableStateOf(Constants.Keys.EMPTY) }
    //var priority by remember { mutableStateOf("") }
/*

    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetElevation = 5.dp,
        sheetShape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
        sheetContent = {
            Surface{
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxSize()
                        .padding(all = 10.dp)
                ){
                    Text(text = Constants.Keys.EDIT_NOTE,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    OutlinedTextField(
                        value = title,
                        onValueChange = {title = it},
                        label = { Text(text="Введите заголовок заметки") },
                        isError = title.isEmpty()
                    )
                    OutlinedTextField(
                        value = subtitle,
                        onValueChange = {subtitle = it},
                        label = { Text(text="Введите текст заметки") },
                        isError = subtitle.isEmpty()
                    )

                    /*Column(Modifier.selectableGroup())
                    {
                        Row{
                            RadioButton(
                                selected = priority == "1",
                                onClick = { priority = "1" },
                                modifier = Modifier.padding(2.dp)
                            )
                            Text("Приоритет: обычный", fontSize = 16.sp)
                        }
                        Row{
                            RadioButton(
                                selected = priority == "2",
                                onClick = { priority = "2" },
                                modifier = Modifier.padding(2.dp)
                            )
                            Text("Приоритет: высокий", fontSize = 16.sp)
                        }
                    }*/



                    Button(
                        modifier = Modifier.padding(top = 2.dp), onClick =
                        {/*
                            viewModel.updateNote(
                                note =
                                Track(id = note.id, title = title, subtitle = subtitle, date = note.date, priority = priority.toInt())
                            )*/
                            { navController.navigate(NavigationPath.List.route) }
                        }
                    )
                    {
                        Text(text = Constants.Keys.UPDATE_NOTE, fontSize = 24.sp)

                    }
                }
            }

        }
    ) {
        //Scaffold(
        //  modifier = Modifier.fillMaxSize()

        // )
        // {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp)
        ) {
            Column(
                modifier = Modifier.padding(vertical = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally

            )
            {
                if (track != null) {
                    Text(
                        //text = track.title,
                        text = "экран одного трека",
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 32.dp)
                    )
                }
                /*
                if (note != null) {
                    Text(
                        text = note.subtitle,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Light,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                }
                if (note != null) {
                    Text(
                        text = DateTimeFormatter
                            .ofPattern("yyyy-MM-dd HH:mm")
                            .withZone( ZoneId.of("Europe/Moscow"))
                            .format(Instant.ofEpochSecond(note.date)),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Light,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                }*/
            }

        }
        Row(
            modifier = Modifier
                .padding(horizontal = 32.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(onClick = { coroutineScope.launch {
                /*
                if (note != null) {
                    title = note.title
                }
                if (note != null) {
                    subtitle = note.subtitle
                }
                if (note != null) {
                    priority = note.priority.toString()
                }*/
                bottomSheetState.show()
            } }) {
                Text(text = Constants.Keys.UPDATE, fontSize = 18.sp)

            }

            Button(onClick = {
                /*
                if (track != null) {
                    viewModel.deleteNote(track = track){navController.navigate(NavigationPath.List.route)}
                }*/
            }) {
                Text(text = Constants.Keys.DELETE, fontSize = 18.sp)
            }
            Button(onClick = { navController.navigate(NavigationPath.List.route) }) {

                Text(text = Constants.Keys.NAV_BACK, fontSize = 18.sp)

            }
        }
        //}
    }
*/
}


/*
@Preview(showBackground = true)
@Composable
fun prevTrackScreen(){
    CoordinatesTrackerTheme {
        val context = LocalContext.current
        val mViewModel: MainViewModel = viewModel(factory = MainViewModelFactory(context.applicationContext as Application))

        TrackScreen(
            navController = rememberNavController(),
            viewModel = mViewModel,
            noteId = "1"
        )
    }
}*/
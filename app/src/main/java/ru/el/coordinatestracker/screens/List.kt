package ru.el.coordinatestracker.screens

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import ru.el.coordinatestracker.FakeViewModel
import ru.el.coordinatestracker.MainViewModel
//import ru.el.coordinatestracker.MainViewModelFactory
import ru.el.coordinatestracker.db.TrackDAO
import ru.el.coordinatestracker.db.TrackRepository
import ru.el.coordinatestracker.db.TrackViewModel
//import ru.el.coordinatestracker.MainViewModelFactory
import ru.el.coordinatestracker.db.entities.Tracks
import ru.el.coordinatestracker.navigation.NavigationPath
import ru.el.coordinatestracker.ui.theme.CoordinatesTrackerTheme
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter


@Composable
fun ListScreen(navController: NavHostController, viewModel: MainViewModel) {

    //var tracks = viewModel.readTracks().collectAsState(listOf()).value
    /*var sort by remember{ mutableStateOf(SORT_BY_PRIORITY) }
    var notes =
        viewModel.readAllNotes().observeAsState(listOf()).value.sortedByDescending { it.priority }

    if (sort == SORT_BY_DATE) {
        notes =
            viewModel.readAllNotes().observeAsState(listOf()).value.sortedByDescending { it.date }
    }*/
    //var tracks = listOf(1)
    //val tracks

    val tracks = viewModel.db.getTracks().collectAsState(listOf()).value

    Column(Modifier.selectableGroup())
    {
        Text(
            text = "экран с треками",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )


        /*Row {
            RadioButton(
                selected = sort == SORT_BY_DATE,
                onClick = { sort = SORT_BY_DATE },
                modifier = Modifier.padding(8.dp)
            )
            Text("Сортировка по времени", fontSize = 20.sp)
        }
        Row {
            RadioButton(
                selected = sort == SORT_BY_PRIORITY,
                onClick = { sort = SORT_BY_PRIORITY },
                modifier = Modifier.padding(8.dp)
            )
            Text("Сортировка по важности", fontSize = 20.sp)
        }*/
        LazyColumn {
            items(tracks) { track ->
                TrackItem(track = track, navController = navController)
            }
        }
    }
    /*
    //BottomAppBar {
    FloatingActionButton(
        onClick = {
            navController.navigate(NavigationPath.Add.route)
        },
        //Modifier.size(100.dp)
        modifier = Modifier.offset(x = 320.dp, y = 16.dp)
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = ADD_NOTE,
            tint = Color.White
        )
    }*/

    //}


}


//}


@Composable
fun TrackItem(track: Tracks, navController: NavHostController) {

    Card(

        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 24.dp)
            .clickable {
                navController.navigate(NavigationPath.Track.route + "/${track.id}")
            },
        elevation = 6.dp
    )
    {
        Column(
            modifier = Modifier.padding(vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = DateTimeFormatter
                    .ofPattern("yyyy-MM-dd HH:mm")
                    .withZone( ZoneId.of("Europe/Moscow"))
                    .format(Instant.ofEpochSecond(track.dateStart)),

                //text = "экран со списком треков",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
               text = DateTimeFormatter
                   .ofPattern("yyyy-MM-dd HH:mm")
                   .withZone( ZoneId.of("Europe/Moscow"))
                   .format(Instant.ofEpochSecond(track.dateEnd)),
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = track.distance.toString(),
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )

        }

    }
}
@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun Previews() {
    CoordinatesTrackerTheme {
        val context = LocalContext.current
        val fakeViewModel = FakeViewModel(application = Application()) // Используем NoteViewModelInterface

        ListScreen(navController = rememberNavController(), viewModel = fakeViewModel)
    }
}
    /*

@Preview(showBackground = true)
@Composable
fun prevListScreen() {
    CoordinatesTrackerTheme {

        val context = LocalContext.current
        val mViewModel: MainViewModel =
            viewModel(factory = MainViewModelFactory(context.applicationContext as Application))

        ListScreen(navController = rememberNavController(), viewModel = mViewModel)
    }

}*/

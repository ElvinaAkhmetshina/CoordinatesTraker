package ru.el.coordinatestracker.screens

import android.app.Application
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ru.el.coordinatestracker.FakeViewModel
import ru.el.coordinatestracker.MainViewModel
import ru.el.coordinatestracker.db.entities.Tracks
//import ru.el.coordinatestracker.MainViewModelFactory
import ru.el.coordinatestracker.navigation.NavigationPath
import ru.el.coordinatestracker.ui.theme.CoordinatesTrackerTheme

@Composable
fun StartScreen(navController: NavHostController, viewModel: MainViewModel) {



    val lastTrackId = viewModel.db.getTracks().collectAsState(listOf()).value.lastIndex



    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {

                    navController.navigate(route = NavigationPath.List.route)

            },
            modifier = Modifier
                .width(250.dp)
                .height(100.dp)
                .padding(vertical = 8.dp)
        )
        {
            Text(
                text = "Просмотреть сохраненные треки",
                fontFamily = FontFamily.Cursive,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
        Button(
            onClick = {
                var dateStart = System.currentTimeMillis() / 1000

                var track = Tracks(dateStart = dateStart, dateEnd = dateStart, distance = 0)
                viewModel.insertAll(track)

                var newTrackId = lastTrackId+2
                navController.navigate(NavigationPath.Add.route + "/${newTrackId}")

            },
            modifier = Modifier
                .width(250.dp)
                .height(100.dp)
                .padding(vertical = 8.dp)
        )
        {
            Text(
                text = "Начать запись нового трека",
                fontFamily = FontFamily.Cursive,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }


    }

}



@Preview(showBackground = true)
@Composable
fun prevStartScreen() {
    CoordinatesTrackerTheme {

        val context = LocalContext.current


        val fakeViewModel = FakeViewModel(application = Application()) // Используем NoteViewModelInterface



        StartScreen(navController = rememberNavController(), viewModel = fakeViewModel)
    }
}

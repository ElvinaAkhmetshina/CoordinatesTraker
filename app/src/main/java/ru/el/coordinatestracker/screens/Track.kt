package ru.el.coordinatestracker.screens

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import ru.el.coordinatestracker.FakeViewModel
import ru.el.coordinatestracker.MainViewModel
import ru.el.coordinatestracker.db.entities.TrackCoordinates
import ru.el.coordinatestracker.db.entities.Tracks
//import ru.el.coordinatestracker.MainViewModelFactory

import ru.el.coordinatestracker.navigation.NavigationPath
import ru.el.coordinatestracker.ui.theme.CoordinatesTrackerTheme
import ru.el.coordinatestracker.utils.Constants
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter


@SuppressLint("SuspiciousIndentation")
@Composable
fun TrackScreen(navController: NavHostController, viewModel: MainViewModel, trackId: String?){
    val tracks = viewModel.db.getTracks().collectAsState(listOf()).value

    val track = tracks.firstOrNull { it.id == trackId?.toInt() } ?: Tracks(
        dateStart = 0,
        dateEnd = 0,
        distance = 0,
    )
    val tI= track.id
    val trackCoordinates = viewModel.db.getTrackCoordinates(tI.toString()).collectAsState(listOf()).value

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
                Text(
                    text = "Дата начала трекинга:",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                    Text(
                        text = DateTimeFormatter
                            .ofPattern("yyyy-MM-dd HH:mm")
                            .withZone( ZoneId.of("Europe/Moscow"))
                            .format(Instant.ofEpochSecond(track.dateStart)),

                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 32.dp)
                    )
                Text(
                    text = "Дата окончания трекинга:",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = DateTimeFormatter
                        .ofPattern("yyyy-MM-dd HH:mm")
                        .withZone( ZoneId.of("Europe/Moscow"))
                        .format(Instant.ofEpochSecond(track.dateEnd)),

                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 32.dp)
                )
                Text(
                    text = "Пройденное расстояние:",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = track.distance.toString(),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 32.dp)
                )
                Text(
                    text = "Список координат X и Y:",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                for(trackC in trackCoordinates){
                Text(
                    text = "|X: |"+trackC.coordinatesX.toString()+"  |Y: | "+trackC.coordinatesY.toString(),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 32.dp)
                )

                }


            }

        }
        Row(
            modifier = Modifier
                .padding(horizontal = 32.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {

            Button(onClick = { navController.navigate(NavigationPath.List.route) }) {

                Text(text = Constants.Keys.NAV_BACK, fontSize = 18.sp)

            }
        }

    }


@Composable
@Preview(showBackground = true)
fun prevTrackScreen() {
    CoordinatesTrackerTheme {
        TrPr()
        //val context = LocalContext.current
        //val fakeViewModel = FakeViewModel(application = Application())
        //TrackScreen(
          //  navController = rememberNavController(),
            //viewModel = fakeViewModel,
            //trackId = "1"
        //)
    }
}



@Composable
fun TrPr()
{Card(
    modifier = Modifier
        .fillMaxWidth()
        .padding(32.dp)
) {
    Column(
        modifier = Modifier.padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    )
    {
        Text(
            text = "Дата начала трекинга:",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "start",

            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 32.dp)
        )
        Text(
            text = "Дата окончания трекинга:",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "end",

            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 32.dp)
        )
        Text(
            text = "Пройденное расстояние:",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "distance",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 32.dp)
        )
        Text(
            text = "Список координат X и Y:",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

            Text(
                text = "|X: |"+"  |Y: | ",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 32.dp)
            )




    }

}
    Row(
        modifier = Modifier
            .padding(horizontal = 32.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {

        Button(onClick = {  }) {

            Text(text = Constants.Keys.NAV_BACK, fontSize = 18.sp)

        }
    }}
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
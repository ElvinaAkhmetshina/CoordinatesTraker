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
import ru.el.coordinatestracker.MainViewModel
import ru.el.coordinatestracker.db.TracksDatabase
//import ru.el.coordinatestracker.MainViewModelFactory
import ru.el.coordinatestracker.navigation.NavigationPath
import ru.el.coordinatestracker.ui.theme.CoordinatesTrackerTheme

@Composable
fun StartScreen(navController: NavHostController, viewModel: MainViewModel) {


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {

                //viewModel.initDatabase{
                    navController.navigate(route = NavigationPath.List.route)
                //}
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
                fontSize = 32.sp
            )
        }
        Button(
            onClick = {
                //viewModel.initDatabase(TYPE_ROOM) {
                   // navController.navigate(route = NavigationPath.List.route)
                //}
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
                fontSize = 32.sp
            )
        }


    }

}
//}

/*
@Preview(showBackground = true)
@Composable
fun prevStartScreen() {
    CoordinatesTrackerTheme {
        val context = LocalContext.current
        val mViewModel: MainViewModel =
            viewModel(factory = MainViewModelFactory(context.applicationContext as Application))

        StartScreen(navController = rememberNavController(), viewModel = mViewModel)
    }
}*/

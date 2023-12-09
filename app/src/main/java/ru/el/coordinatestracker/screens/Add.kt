package ru.el.coordinatestracker.screens

import android.app.Application
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import ru.el.coordinatestracker.MainViewModel
import ru.el.coordinatestracker.MainViewModelFactory
import ru.el.coordinatestracker.db.model.Track
import ru.el.coordinatestracker.navigation.NavigationPath
import ru.el.coordinatestracker.ui.theme.CoordinatesTrackerTheme
import ru.el.coordinatestracker.utils.Constants
import ru.el.coordinatestracker.utils.Constants.Keys.ADD_NOTE

@Composable
fun AddScreen(navController: NavHostController, viewModel: MainViewModel){
    var title by remember{ mutableStateOf("") }
    var subtitle by remember{ mutableStateOf("") }

    var priority by remember{ mutableStateOf("") }
    var isButtonEnabled by remember{ mutableStateOf(false) }


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
        )
        OutlinedTextField(value = title, onValueChange = {
            title = it
            isButtonEnabled = title.isNotEmpty() && subtitle.isNotEmpty()
        }, label = { Text(text = "Введите заголовок заметки") }, isError = title.isEmpty())
        OutlinedTextField(value = subtitle, onValueChange = {
            subtitle = it
            isButtonEnabled = title.isNotEmpty() && subtitle.isNotEmpty()
        }, label = { Text(text = "Введите текст заметки") }, isError = subtitle.isEmpty())

        Column(Modifier.selectableGroup())
        {
            Row{
                RadioButton(
                    selected = priority == "1",
                    onClick = { priority = "1" },
                    modifier = Modifier.padding(8.dp)
                )
                Text("Приоритет: обычный", fontSize = 24.sp)
            }
            Row{
                RadioButton(
                    selected = priority == "2",
                    onClick = { priority = "2" },
                    modifier = Modifier.padding(8.dp)
                )
                Text("Приоритет: высокий", fontSize = 24.sp)
            }
        }
        Button(
            modifier = Modifier.padding(top = 16.dp),
            enabled = isButtonEnabled,
            onClick = {/*
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
            Text(text = ADD_NOTE, fontSize = 24.sp)
        }
        Button(

            onClick = { navController.navigate(NavigationPath.List.route) }) {

            Text(text = Constants.Keys.NAV_BACK, fontSize = 24.sp)

        }
    }
//}
}

@Preview(showBackground = true)
@Composable
fun prevAddScreen(){
    CoordinatesTrackerTheme {
        val context = LocalContext.current
        val mViewModel: MainViewModel = viewModel(factory = MainViewModelFactory(context.applicationContext as Application))

        AddScreen(navController = rememberNavController(), viewModel = mViewModel)
    }
}
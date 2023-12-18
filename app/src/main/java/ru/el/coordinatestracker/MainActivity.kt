package ru.el.coordinatestracker

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.location.Location
import android.location.LocationManager
import android.location.LocationRequest
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationListener
import com.google.android.gms.location.LocationResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import ru.el.coordinatestracker.db.TrackRepository
import ru.el.coordinatestracker.db.TracksDatabase
import ru.el.coordinatestracker.db.entities.TrackCoordinates
//import ru.el.coordinatestracker.db.entities.TrackWithCoordinates
import ru.el.coordinatestracker.db.entities.Tracks
import ru.el.coordinatestracker.navigation.NavigationPath
import ru.el.coordinatestracker.navigation.TracksNavigationHost
import ru.el.coordinatestracker.ui.theme.CoordinatesTrackerTheme


class MainActivity : ComponentActivity() {




    private val mvm: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }


    //val repository by lazy { TrackRepository(TracksDatabase.) }
    private val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        mvm.showRequestDialog = false
        when {
            permissions.getOrDefault(ACCESS_FINE_LOCATION, false) -> {

            }

            permissions.getOrDefault(ACCESS_COARSE_LOCATION, false) -> {
            }

            else -> {
                finish()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        lifecycleScope.launch {
            TracksDatabase.getDao(applicationContext).apply {
                //insertTrack(t1)
                //insertTrackCoordinates(t2)
               val tracks = getTracks()
                //Log.i("TRACKS", getTracks().joinToString())
            }
        }
        setContent {
            CoordinatesTrackerTheme {
                MainUI(
                    mvm,
                    Modifier.fillMaxSize()
                )
                mvm.showRequestDialog =
                    !mvm.isPermissionsGranted(
                        ACCESS_COARSE_LOCATION,
                        ACCESS_FINE_LOCATION,
                        context = this
                    )
                if (mvm.showRequestDialog) {
                    LocationRequestDialog(
                        onDeny = {
                            finish()
                        }
                    ) {
                        // Формирование запроса из системы на доступ к геолокации
                        mvm.showRequestDialog = false
                        locationPermissionRequest.launch(
                            arrayOf(
                                ACCESS_FINE_LOCATION,
                                ACCESS_COARSE_LOCATION
                            )
                        )
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (mvm.requestLocationUpdates) mvm.startLocationUpdates()
    }

    override fun onPause() {
        super.onPause()
        mvm.stopLocationUpdates()
    }





}







//my working code
    /*
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        val dao = TracksDatabase.getInstance(this).trackDAO
        super.onCreate(savedInstanceState)
        setContent {
            CoordinatesTrackerTheme {
                val context = LocalContext.current
                val mViewModel: MainViewModel =
                    viewModel(factory = MainViewModelFactory(context.applicationContext as Application))

                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    text = "ELVINA_AKHMETSHINA",
                                    fontFamily = FontFamily.Cursive,
                                    fontStyle = FontStyle.Italic,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 32.sp
                                )
                            },
                            backgroundColor = main_color,
                            contentColor = Color.White,
                            elevation = 20.dp

                        )

                    },
                    content = {
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colors.background
                        ) {
                            TracksNavigationHost(mViewModel)
                        }
                    }
                )


            }
            val dao = TracksDatabase.getInstance(this).trackDAO
            val schools = listOf(
                Tracks(1111, 1111)
            )


            val students = listOf(
                TrackCoordinates(1111, 2222)
            )

            lifecycleScope.launch {

                schools.forEach { dao.insertTrack(it) }

                students.forEach { dao.insertTrackCoordinates(it) }
                //работало
                /*
            val dao = TracksDatabase.getInstance(this).TrackDAO


            val schools = listOf(
                Tracks(1111, 1111)
            )


            val students = listOf(
                TrackCoordinates(1111, 2222)
            )

            lifecycleScope.launch {

                schools.forEach { dao.insertTrack(it) }

                students.forEach { dao.insertTrackCoordinates(it) }


                //val schoolWithStudents = dao.getSchoolWithStudents("Kotlin School")
            }*/


            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CoordinatesTrackerTheme {

    }
}*/








@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainUI(
    mvm: MainViewModel,
    modifier: Modifier = Modifier,
){
    val loc by mvm.location.collectAsState()
    val main_color = Color(0xFF3789CA)
    val locStr = loc?.let{ "Lat: ${it.latitude} Lon: ${it.longitude}" } ?: "Unknown location"



    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "ELVINA_AKHMETSHINA",
                        fontFamily = FontFamily.Cursive,
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.Bold,
                        fontSize = 32.sp
                    )
                },
                backgroundColor = main_color,
                contentColor = Color.White,
                elevation = 20.dp

            )

        },
        content = {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {


                TracksNavigationHost(mvm)
            }
        }
    )
}

//@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationRequestDialog(
    modifier: Modifier = Modifier,
    onDeny: ()->Unit,
    onAllow: ()->Unit,
){
    AlertDialog(

        title = {
            Text(text = "Внимание!")
        },
        text = {
            Text(text = "Данное приложение хочет собирать ваши координаты, вы доверяте ему или просто так сюда зашли?")
        },
        onDismissRequest = {
                           onDeny()
            /*onDismissRequest()*/
        },
        confirmButton = {
            TextButton(
                onClick = {
                    /*onConfirmation()*/
                    onAllow()
                }
            ) {
                Text("Разрешаю")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {

                    /*onDismissRequest()*/
                }
            ) {
                Text("Отказываюсь и ухожу")
            }
        }
    )
    /*
    AlertDialog(
        onDismissRequest = { onDeny() },
        confirmButton = {onAllow()}
    ) 
        Card(
            modifier = modifier.shadow(3.dp, shape = RoundedCornerShape(20.dp))
        ) {
            Column(
                modifier = Modifier
                    .padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painterResource(id = R.drawable.twotone_not_listed_location_48),
                    contentDescription = null,
                    tint = colorResource(id = R.color.black)
                )
                Text(stringResource(R.string.loc_permission_request))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    Button(onClick = { onDeny() }) {
                        Text("No")
                    }
                    Button(onClick = { onAllow() }) {
                        Text("Yes")
                    }
                }
            }
        }*/
    }


@Preview
@Composable
fun LocationRequestDialogPreview(){
    LocationRequestDialog(onDeny = {  }) {

    }
}

/*
@Preview
@Composable
fun MainUIPreview(){
    CoordinatesTrackerTheme {
        MainUI(
            mvm = MainViewModel(Application())
            //Modifier.fillMaxSize(),
            //title = "Заголовок активности",
            //viewMode = ViewMode.NOTE,
        )
    }
}*/





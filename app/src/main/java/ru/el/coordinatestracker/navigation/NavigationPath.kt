package ru.el.coordinatestracker.navigation

import androidx.compose.runtime.Composable
import ru.el.coordinatestracker.MainViewModel
import ru.el.coordinatestracker.screens.AddScreen
//import ru.el.coordinatestracker.screens.ListScreen
import ru.el.coordinatestracker.screens.StartScreen
import ru.el.coordinatestracker.utils.Constants
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.el.coordinatestracker.screens.ListScreen
//import ru.el.coordinatestracker.screens.SaveScreen
import ru.el.coordinatestracker.screens.TrackScreen

//import ru.el.coordinatestracker.screens.TrackScreen

//import ru.el.coordinatestracker.screens.TrackScreen

sealed class NavigationPath(val route: String) {
    object Start: NavigationPath(Constants.Screens.START_SCREEN)
    object List: NavigationPath(Constants.Screens.MAIN_SCREEN)
    object Add: NavigationPath(Constants.Screens.ADD_SCREEN)
    object Track: NavigationPath(Constants.Screens.NOTE_SCREEN)
    //object Save: NavigationPath(Constants.Screens.SAVE_SCREEN)
}

@Composable
fun TracksNavigationHost(mViewModel: MainViewModel) {
    val navController = rememberNavController()

    NavHost(navController=navController, startDestination=NavigationPath.Start.route){
        composable(NavigationPath.Start.route) {StartScreen(navController=navController, viewModel = mViewModel)}
        //composable(NavigationPath.Add.route) {AddScreen(navController = navController, viewModel = mViewModel) }
        composable(NavigationPath.List.route) {ListScreen(navController = navController, viewModel = mViewModel) }
        //composable(NavigationPath.Save.route) {SaveScreen(navController = navController, viewModel = mViewModel) }
        composable(NavigationPath.Add.route + "/{${Constants.Keys.ID}}") {backStackEntry ->  AddScreen(
            navController = navController, viewModel = mViewModel, trackId =backStackEntry.arguments?.getString(Constants.Keys.ID)) }
        composable(NavigationPath.Track.route + "/{${Constants.Keys.ID}}") {backStackEntry ->  TrackScreen(
        navController = navController, viewModel = mViewModel, trackId = backStackEntry.arguments?.getString(Constants.Keys.ID)) }
    }
}
package sheridan.bajajku.assignment4.ui.navigation

import android.app.Activity
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import sheridan.bajajku.assignment4.WeatherViewModel
import sheridan.bajajku.assignment4.ui.AppScreens.BramptonScreen
import sheridan.bajajku.assignment4.ui.AppScreens.MississaugaScreen
import sheridan.bajajku.assignment4.ui.AppScreens.OakvilleScreen

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun WeatherNavHost(navController: NavHostController) {
    val widthSizeClass = calculateWindowSizeClass(LocalContext.current as Activity).widthSizeClass

    NavHost(navController, startDestination = OakvilleDestination.route) {
        composable(route = OakvilleDestination.route){
            val viewModel: WeatherViewModel = hiltViewModel()
            OakvilleScreen(
                location = "Oakville,CA",
                viewModel = viewModel,
                onTabPressed = tabNavigate(navController),
                widthSize = widthSizeClass
            )
        }
        composable(route = BramptonDestination.route) {
            val viewModel: WeatherViewModel = hiltViewModel()
            BramptonScreen(
                location = "Brampton, CA",
                viewModel = viewModel,
                onTabPressed = tabNavigate(navController),
                navigateBack = { navController.popBackStack() },
                widthSize = widthSizeClass
            )
        }
        composable(route = MississaugaDestination.route) {
            val viewModel: WeatherViewModel = hiltViewModel()
            MississaugaScreen(
                location = "Mississauga, CA",
                viewModel = viewModel,
                onTabPressed = tabNavigate(navController),
                navigateBack = { navController.popBackStack() },
                widthSize = widthSizeClass
            )
        }
    }
}

@Composable
private fun tabNavigate(navController: NavHostController): (String) -> Unit = { route: String ->
    if(route == OakvilleDestination.route) {
        navController.popBackStack(
            route = OakvilleDestination.route, inclusive = false
        )
    } else {
        navController.navigate(route)
    }
}
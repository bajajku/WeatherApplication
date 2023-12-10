package sheridan.bajajku.assignment4.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import sheridan.bajajku.assignment4.WeatherViewModel
import sheridan.bajajku.assignment4.ui.AppScreens.BramptonScreen
import sheridan.bajajku.assignment4.ui.AppScreens.MississaugaScreen
import sheridan.bajajku.assignment4.ui.AppScreens.OakvilleScreen

@Composable
fun WeatherNavHost(navController: NavHostController) {
    NavHost(navController, startDestination = OakvilleDestination.route) {
        composable(route = OakvilleDestination.route){
            val viewModel: WeatherViewModel = hiltViewModel()
            OakvilleScreen(
                location = "Oakville,CA",
                viewModel = viewModel,
                onTabPressed = tabNavigate(navController)
            )
        }
        composable(route = BramptonDestination.route) {
            val viewModel: WeatherViewModel = hiltViewModel()
            BramptonScreen(
                location = "Brampton, CA",
                viewModel = viewModel,
                onTabPressed = tabNavigate(navController),
                navigateBack = { navController.popBackStack() }
            )
        }
        composable(route = MississaugaDestination.route) {
            val viewModel: WeatherViewModel = hiltViewModel()
            MississaugaScreen(
                location = "Mississauga, CA",
                viewModel = viewModel,
                onTabPressed = tabNavigate(navController),
                navigateBack = { navController.popBackStack() })
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
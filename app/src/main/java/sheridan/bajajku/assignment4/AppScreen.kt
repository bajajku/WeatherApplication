package sheridan.bajajku.assignment4

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import sheridan.bajajku.assignment4.ui.navigation.WeatherNavHost

@Composable
fun AppScreen(navHostController: NavHostController = rememberNavController()) {
    WeatherNavHost(navController = navHostController)
}
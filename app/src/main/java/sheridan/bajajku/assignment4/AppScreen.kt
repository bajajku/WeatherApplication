package sheridan.bajajku.assignment4

import android.app.Activity
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import sheridan.bajajku.assignment4.ui.navigation.WeatherNavHost

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun AppScreen(navHostController: NavHostController = rememberNavController()) {
    WeatherNavHost(navController = navHostController)
}
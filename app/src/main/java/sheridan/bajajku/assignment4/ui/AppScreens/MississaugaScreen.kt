package sheridan.bajajku.assignment4.ui.AppScreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import sheridan.bajajku.assignment4.WeatherViewModel
import sheridan.bajajku.assignment4.domain.WeatherResponse
import sheridan.bajajku.assignment4.ui.common.ScreenContent
import sheridan.bajajku.assignment4.ui.common.WeatherBottomBar
import sheridan.bajajku.assignment4.ui.common.WeatherNavigationDrawer
import sheridan.bajajku.assignment4.ui.common.WeatherNavigationRail
import sheridan.bajajku.assignment4.ui.common.WeatherTopAppBar
import sheridan.bajajku.assignment4.ui.navigation.BramptonDestination
import sheridan.bajajku.assignment4.ui.navigation.MississaugaDestination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MississaugaScreen (
    location: String,
    viewModel: WeatherViewModel,
    onTabPressed: (String) -> Unit,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    widthSize: WindowWidthSizeClass

) {
    val weather by viewModel.weather.observeAsState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    LaunchedEffect(location) {
        viewModel.getWeather(location)
    }

    when (widthSize) {
        WindowWidthSizeClass.Compact -> { MississaugaCompactScreen(modifier, scrollBehavior, navigateBack, onTabPressed, weather) }
        WindowWidthSizeClass.Medium -> { MississaugaMediumScreen(modifier, scrollBehavior, navigateBack, onTabPressed, weather) }
        WindowWidthSizeClass.Expanded -> { MississaugaExpandedScreen(modifier, scrollBehavior, navigateBack, onTabPressed, weather) }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MississaugaCompactScreen(modifier: Modifier, scrollBehavior: TopAppBarScrollBehavior, navigateBack: ()->Unit, onTabPressed: (String) -> Unit, weather: WeatherResponse?) {
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection
        ),
        topBar = {
            WeatherTopAppBar(
                title = stringResource(MississaugaDestination.titleRes),
                canNavigateBack = true,
                navigateUp = navigateBack,
                scrollBehavior = scrollBehavior
            )
        },
        bottomBar = {
            WeatherBottomBar(
                currentRoute = MississaugaDestination.route,
                onTabPressed = onTabPressed
            )
        }
    ) {innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("HMC Campus", fontSize = 24.sp) // Set the desired font size
            Text("Mississauga, CA", fontSize = 24.sp)
            Spacer(modifier = Modifier.height(24.dp))

            if (weather != null) {
                Text("Temperature: ${weather!!.main.temp} °C", fontSize = 20.sp)
                Text("Feels Like: ${weather!!.main.feels_like} °C", fontSize = 20.sp)
                Text("Minimum Temperature: ${weather!!.main.temp_min} °C", fontSize = 20.sp)
                Text("Maximum Temperature: ${weather!!.main.temp_max} °C", fontSize = 20.sp)
                Text("Pressure: ${weather!!.main.pressure}", fontSize = 20.sp)
                Text("Humidity: ${weather!!.main.humidity}", fontSize = 20.sp)
            } else {
                Text("Loading...", fontSize = 20.sp)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MississaugaMediumScreen(modifier: Modifier, scrollBehavior: TopAppBarScrollBehavior, navigateBack: ()->Unit, onTabPressed: (String) -> Unit, weather: WeatherResponse?) {
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            WeatherTopAppBar(
                title = stringResource(MississaugaDestination.titleRes),
                canNavigateBack = true,
                navigateUp = navigateBack,
                scrollBehavior = scrollBehavior
            )
        },
        bottomBar = {
            WeatherNavigationRail(
                currentRoute = MississaugaDestination.route,
                onTabPressed = onTabPressed,
                weather = weather,
                "HMC Campus",
                "Mississauga, CA"
            )
        }
    ) {innerPadding ->
        ScreenContent(
            modifier = Modifier
                .padding(innerPadding),
            weather = weather,
            "HMC Campus",
            "Mississauga, CA"
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MississaugaExpandedScreen(modifier: Modifier, scrollBehavior: TopAppBarScrollBehavior, navigateBack: ()->Unit, onTabPressed: (String) -> Unit, weather: WeatherResponse?) {
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            WeatherTopAppBar(
                title = stringResource(MississaugaDestination.titleRes),
                canNavigateBack = true,
                navigateUp = navigateBack,
                scrollBehavior = scrollBehavior
            )
        },
        bottomBar = {
            WeatherNavigationDrawer(
                currentRoute = MississaugaDestination.route,
                onTabPressed = onTabPressed
            )
        }
    ) {innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("HMC Campus")
            Text("Mississauga, CA")
            Spacer(modifier = Modifier.height(24.dp))
            if (weather != null) {
                Text("Temperature: ${weather!!.main.temp} °C")
                Text("Feels Like: ${weather!!.main.feels_like} °C")
                Text("Minimum Temperature: ${weather!!.main.temp_min} °C")
                Text("Maximum Temperature: ${weather!!.main.temp_max} °C")
                Text("Pressure: ${weather!!.main.pressure}")
                Text("Humidity: ${weather!!.main.humidity}")
            } else {
                Text("Loading...")
            }
        }
    }
}
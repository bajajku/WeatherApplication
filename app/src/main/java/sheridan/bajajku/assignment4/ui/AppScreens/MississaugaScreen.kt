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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import sheridan.bajajku.assignment4.WeatherViewModel
import sheridan.bajajku.assignment4.ui.common.WeatherBottomBar
import sheridan.bajajku.assignment4.ui.common.WeatherTopAppBar
import sheridan.bajajku.assignment4.ui.navigation.MississaugaDestination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MississaugaScreen (
    location: String,
    viewModel: WeatherViewModel,
    onTabPressed: (String) -> Unit,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val weather by viewModel.weather.observeAsState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    LaunchedEffect(location) {
        viewModel.getWeather(location)
    }

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
            Text("HMC Campus")
            Text("Mississauga, CA")
            Spacer(modifier = Modifier.height(24.dp))
            if (weather != null) {
                Text("Temperature: ${weather!!.main.temp}")
                Text("Feels Like: ${weather!!.main.feels_like}")
                Text("Minimum Temperature: ${weather!!.main.temp_min}")
                Text("Maximum Temperature: ${weather!!.main.temp_max}")
                Text("Pressure: ${weather!!.main.pressure}")
                Text("Humidity: ${weather!!.main.humidity}")
            } else {
                Text("Loading...")
            }
        }
    }


}
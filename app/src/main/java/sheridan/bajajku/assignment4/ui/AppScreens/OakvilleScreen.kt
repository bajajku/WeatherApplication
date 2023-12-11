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
import androidx.compose.ui.unit.sp
import sheridan.bajajku.assignment4.WeatherViewModel
import sheridan.bajajku.assignment4.ui.common.WeatherBottomBar
import sheridan.bajajku.assignment4.ui.common.WeatherTopAppBar
import sheridan.bajajku.assignment4.ui.navigation.OakvilleDestination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OakvilleScreen (
    location: String,
    viewModel: WeatherViewModel,
    onTabPressed: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val weather by viewModel.weather.observeAsState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    LaunchedEffect(location) {
        viewModel.getWeather(location)
    }

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            WeatherTopAppBar(
                title = stringResource(OakvilleDestination.titleRes),
                canNavigateBack = false,
                scrollBehavior = scrollBehavior
            )
        },
        bottomBar = {
            WeatherBottomBar(
                currentRoute = OakvilleDestination.route,
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
            Text("Trafalgar Campus", fontSize=24.sp)
            Text("Oakville, CA", fontSize=24.sp)
            Spacer(modifier = Modifier.height(24.dp))
            if (weather != null) {
                Text("Temperature: ${weather!!.main.temp}", fontSize=20.sp)
                Text("Feels Like: ${weather!!.main.feels_like}", fontSize=20.sp)
                Text("Minimum Temperature: ${weather!!.main.temp_min}", fontSize=20.sp)
                Text("Maximum Temperature: ${weather!!.main.temp_max}", fontSize=20.sp)
                Text("Pressure: ${weather!!.main.pressure}", fontSize=20.sp)
                Text("Humidity: ${weather!!.main.humidity}", fontSize=20.sp)
            } else {
                Text("Loading...")
            }
        }
    }


}
package sheridan.bajajku.assignment4.ui.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import sheridan.bajajku.assignment4.ui.navigation.BramptonDestination
import sheridan.bajajku.assignment4.ui.navigation.MississaugaDestination
import sheridan.bajajku.assignment4.ui.navigation.OakvilleDestination

@Composable
fun WeatherNavigationDrawer(
    currentRoute: String,
    onTabPressed: (String) -> Unit
) {
    val navigationItemContentList = listOf(
        NavigationItemContent(
            route = OakvilleDestination.route,
            icon = Icons.Default.Home,
            text = "Trafalgar"
        ),
        NavigationItemContent(
            route = BramptonDestination.route,
            icon = Icons.Default.LocationOn,
            text = "Davis"
        ),
        NavigationItemContent(
            route = MississaugaDestination.route,
            icon = Icons.Default.LocationOn,
            text = "HMC"
        )
    )
        PermanentNavigationDrawer(
            drawerContent = {
                navigationItemContentList.forEach { item ->
                    NavigationDrawerItem(
                        icon = { Icon(item.icon, contentDescription = null) },
                        selected = currentRoute == item.route,
                        onClick = { onTabPressed(item.route) },
                        label = { item.text }
                    )
                }
            },
            content = {
                // Other content
            }
        )
}
package sheridan.bajajku.assignment4.ui.theme.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

private data class NavigationItemContent(
    val route: String,
    val icon: ImageVector,
    val text: String
)

@Composable
fun WeatherBottomBar(
    currentRoute: String,
    onTabPressed: (String) -> Unit,
    modifier: Modifier = Modifier
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

    NavigationBar(modifier = modifier) {
        for (navItem in navigationItemContentList) {
            NavigationBarItem(
                selected = currentRoute == navItem.route,
                onClick = { onTabPressed(navItem.route) },
                icon = {
                    Icon(
                        imageVector = navItem.icon,
                        contentDescription = navItem.text
                    )
                },
                label = { Text(text = navItem.text) }
            )
        }
    }
}
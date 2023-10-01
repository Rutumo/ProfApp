package fr.eric.profapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import fr.eric.profapp.ui.theme.ProfAppTheme

sealed class AppScreen(val route: String, @StringRes val resourceId: Int, val icon: ImageVector){
    object Troctor : AppScreen("screen_troctor",R.string.troctor, Icons.Default.Home)
    object Maintenance : AppScreen("screen_maintenace", R.string.maintenance, Icons.Default.Settings)
    object Shop : AppScreen("screen_shop", R.string.shop, Icons.Default.ShoppingCart)
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProfAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppLayout()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppLayout(modifier: Modifier = Modifier) {

    val bottomListItems = listOf(
        AppScreen.Troctor,
        AppScreen.Maintenance,
        AppScreen.Shop
    )

    val navController = rememberNavController()

    Scaffold(modifier = modifier,
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                bottomListItems.forEach{
                    screen ->
                    BottomNavigationItem(
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route  } == true,
                        onClick = { navController.navigate(screen.route){
                            popUpTo(navController.graph.findStartDestination().id){
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true

                        } },
                        icon = { Icon(imageVector =screen.icon , contentDescription = null) })

                }
            }
        }) {
        innerPadding ->
        NavHost(
            navController = navController,
            startDestination = AppScreen.Maintenance.route,
            modifier = Modifier.padding(innerPadding)){
            composable(AppScreen.Troctor.route){ Text(text = "Tractor")}
            composable(AppScreen.Maintenance.route){ Text(text = "Miantenance")}
            composable(AppScreen.Shop.route){ Text(text = "Shop")}
            co
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppLayoutPreview() {
    ProfAppTheme {
        AppLayout()
    }
}
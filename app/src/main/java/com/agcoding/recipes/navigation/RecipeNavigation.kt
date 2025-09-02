package com.agcoding.recipes.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.agcoding.recipes.screens.FavouriteScreen
import com.agcoding.recipes.screens.MainScreen
import com.agcoding.recipes.screens.viewmodel.MainViewModel
import androidx.compose.ui.Modifier
@Composable
fun RecipesNavigation() {
    val navController = rememberNavController()
    val sharedViewModel : MainViewModel = hiltViewModel()
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Favourites
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
                items.forEach { item ->
                    NavigationBarItem(
                        selected = currentRoute == item.route,
                        onClick = {
                            if (currentRoute != item.route) {
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        },
                        icon = { Icon(item.icon, contentDescription = item.title) },
                    )
                }
            }
        }
    ){
        NavHost(
            modifier = Modifier
                .padding(it),
            navController = navController,
            startDestination = BottomNavItem.Home.route,
        ) {
            composable(BottomNavItem.Home.route) {
                MainScreen(navController = navController , sharedViewModel)
            }
            composable(BottomNavItem.Favourites.route) {
                FavouriteScreen(navController = navController , sharedViewModel)
            }
        }
    }

}
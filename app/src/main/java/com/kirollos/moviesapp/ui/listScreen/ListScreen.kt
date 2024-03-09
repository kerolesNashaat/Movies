package com.kirollos.moviesapp.ui.listScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.kirollos.moviesapp.ui.listScreen.appbar.GetBottomAppBar
import com.kirollos.moviesapp.ui.main.Destinations
import com.kirollos.moviesapp.ui.main.ListScreenNavigation
import com.kirollos.moviesapp.ui.utils.currentRoute

@Composable
fun ListScreen() {
    val navController = rememberNavController()

    val shouldShowBottomNavigation = when (currentRoute(navController)) {
        Destinations.NowPlayingScreen.route,
        Destinations.PopularScreen.route,
        Destinations.UpcomingScreen.route -> true

        else -> false
    }

    Scaffold(
        bottomBar = { if (shouldShowBottomNavigation) GetBottomAppBar(navController) }
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(
                PaddingValues(bottom = innerPadding.calculateBottomPadding())
            )
        ) {
            ListScreenNavigation(navController)
        }
    }
}
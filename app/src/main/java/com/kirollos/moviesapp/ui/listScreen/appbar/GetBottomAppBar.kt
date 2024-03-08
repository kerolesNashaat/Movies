package com.kirollos.moviesapp.ui.listScreen.appbar

import androidx.compose.foundation.layout.height
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.kirollos.common.components.GetText
import com.kirollos.common.size_60dp
import com.kirollos.moviesapp.R

@Composable
fun GetBottomAppBar(navController: NavHostController) {
    BottomAppBar(
        modifier = Modifier.height(size_60dp),
        contentColor = Color.Transparent,
        containerColor = Color.Transparent
    ) {
        BottomNavigationBar(navController)
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val tabs = listOf(
        BottomBarItem.NowPlaying(mTitle = stringResource(id = R.string.nowPlaying)),
        BottomBarItem.Popular(mTitle = stringResource(id = R.string.popular)),
        BottomBarItem.Upcoming(mTitle = stringResource(id = R.string.upcoming)),
    )

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntry?.destination

    NavigationBar(
        contentColor = Color.Transparent,
        containerColor = Color.Transparent
    ) {
        tabs.forEach { item ->
            val isSelected = currentDestination?.hierarchy?.any {
                it.route == item.route
            }
            NavigationBarItem(
                selected = isSelected ?: false,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                }, icon = { },
                label = {
                    GetText(
                        text = item.title,
                        color = if (isSelected == true) MaterialTheme.colorScheme.tertiary
                        else MaterialTheme.colorScheme.secondary
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent
                )
            )
        }

    }
}

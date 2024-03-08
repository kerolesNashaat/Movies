package com.kirollos.moviesapp.ui.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kirollos.moviesapp.ui.listScreen.ListScreen
import com.kirollos.moviesapp.ui.listScreen.nowPlaying.NowPlayingScreen
import com.kirollos.moviesapp.ui.listScreen.popular.PopularScreen
import com.kirollos.moviesapp.ui.listScreen.upcoming.UpcomingScreen
import com.kirollos.moviesapp.ui.movieDetailScreen.MovieDetailScreen
import com.kirollos.moviesapp.ui.splashScreen.SplashScreen
import com.kirollos.moviesapp.ui.utils.PARAM_MOVIE_ID

@Composable
fun MainNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Destinations.SplashScreen.route) {
        composable(route = Destinations.SplashScreen.route) {
            SplashScreen {
                navController.navigate(Destinations.ListScreen.route) {
                    popUpTo(Destinations.SplashScreen.route) {
                        inclusive = true
                    }
                }
            }
        }

        composable(route = Destinations.ListScreen.route) {
            ListScreen()
        }
    }
}

@Composable
fun ListScreenNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Destinations.NowPlayingScreen.route
    ) {
        composable(route = Destinations.NowPlayingScreen.route) {
            NowPlayingScreen(onCardClick = { movieId ->
                navController.navigate(Destinations.MovieDetailScreen.addParams(movieId))
            })
        }
        composable(route = Destinations.PopularScreen.route) {
            PopularScreen(onCardClick = { movieId ->
                navController.navigate(Destinations.MovieDetailScreen.addParams(movieId))
            })
        }
        composable(route = Destinations.UpcomingScreen.route) {
            UpcomingScreen(onCardClick = { movieId ->
                navController.navigate(Destinations.MovieDetailScreen.addParams(movieId))
            })
        }
        composable(
            route = Destinations.MovieDetailScreen.route,
            arguments = listOf(navArgument(PARAM_MOVIE_ID) { type = NavType.StringType })
        ) {
            MovieDetailScreen()
        }
    }

}


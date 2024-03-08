package com.kirollos.moviesapp.ui.main

import com.kirollos.moviesapp.ui.utils.PARAM_MOVIE_ID

sealed class Destinations(val route: String) {
    data object SplashScreen : Destinations(splashScreen)
    data object ListScreen : Destinations(listScreen)
    data object NowPlayingScreen : Destinations(nowPlayingScreen)
    data object PopularScreen : Destinations(popularScreen)
    data object UpcomingScreen : Destinations(upcomingScreen)
    data object MovieDetailScreen : Destinations(movieDetailScreen) {
        fun addParams(movieId: Int) =
            movieDetailScreen.replace("{$PARAM_MOVIE_ID}", movieId.toString())
    }
}

private const val splashScreen = "splash"
private const val listScreen = "list"
private const val nowPlayingScreen = "nowPlaying"
private const val popularScreen = "popular"
private const val upcomingScreen = "upcoming"
private const val movieDetailScreen = "movieDetail/{$PARAM_MOVIE_ID}"
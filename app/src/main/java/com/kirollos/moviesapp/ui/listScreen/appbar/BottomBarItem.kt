package com.kirollos.moviesapp.ui.listScreen.appbar

import com.kirollos.moviesapp.ui.main.Destinations

sealed class BottomBarItem(val title: String, val route: String) {
    data class NowPlaying(
        val mTitle: String,
        val mRoute: String = Destinations.NowPlayingScreen.route
    ) :
        BottomBarItem(mTitle, Destinations.NowPlayingScreen.route)

    data class Popular(
        val mTitle: String, val mRoute: String = Destinations.PopularScreen.route
    ) :
        BottomBarItem(mTitle, Destinations.PopularScreen.route)

    data class Upcoming(
        val mTitle: String, val mRoute: String = Destinations.UpcomingScreen.route
    ) :
        BottomBarItem(mTitle, Destinations.UpcomingScreen.route)
}
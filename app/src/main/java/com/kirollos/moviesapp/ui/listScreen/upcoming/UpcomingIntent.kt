package com.kirollos.moviesapp.ui.listScreen.upcoming

sealed class UpcomingIntent {
    data object GetUpcomingMovies : UpcomingIntent()

    data object GetConfigurations : UpcomingIntent()
}
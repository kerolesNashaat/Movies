package com.kirollos.moviesapp.ui.listScreen.upcoming

sealed class UpcomingIntent {
    data class GetUpcomingMovies(val language: String, val page: Int) : UpcomingIntent()

    data object GetConfigurations : UpcomingIntent()
}
package com.kirollos.moviesapp.ui.listScreen.upcoming

sealed class UpcomingIntent {
    data class GetUpcomingMovies(val page: Int) : UpcomingIntent()
    data class LoadMoreMovies(val page: Int) : UpcomingIntent()
    data object GetConfigurations : UpcomingIntent()
}
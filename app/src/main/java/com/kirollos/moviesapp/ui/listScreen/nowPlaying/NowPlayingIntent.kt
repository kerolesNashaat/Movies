package com.kirollos.moviesapp.ui.listScreen.nowPlaying

sealed class NowPlayingIntent {
    data class GetNowPlayingMovies(val language: String, val page: Int) : NowPlayingIntent()
    data object GetConfigurations : NowPlayingIntent()
}
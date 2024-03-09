package com.kirollos.moviesapp.ui.listScreen.nowPlaying

sealed class NowPlayingIntent {
    data object GetNowPlayingMovies : NowPlayingIntent()
    data object GetConfigurations : NowPlayingIntent()
}
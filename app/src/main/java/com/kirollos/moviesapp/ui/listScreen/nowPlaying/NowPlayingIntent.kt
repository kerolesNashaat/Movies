package com.kirollos.moviesapp.ui.listScreen.nowPlaying

sealed class NowPlayingIntent {
    data class GetNowPlayingMovies(val page: Int) : NowPlayingIntent()
    data class LoadMoreMovies(val page: Int) : NowPlayingIntent()
    data object GetConfigurations : NowPlayingIntent()
}
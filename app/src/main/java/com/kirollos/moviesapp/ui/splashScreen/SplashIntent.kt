package com.kirollos.moviesapp.ui.splashScreen

sealed class SplashIntent {
    data object DoSplashTask : SplashIntent()
}
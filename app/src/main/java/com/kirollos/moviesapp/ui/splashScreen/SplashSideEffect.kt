package com.kirollos.moviesapp.ui.splashScreen

sealed class SplashSideEffect {
    data object Navigate : SplashSideEffect()
}
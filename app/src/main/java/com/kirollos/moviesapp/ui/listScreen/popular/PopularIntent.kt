package com.kirollos.moviesapp.ui.listScreen.popular

sealed class PopularIntent {
    data object GetPopularMovies : PopularIntent()

    data object GetConfigurations : PopularIntent()
}
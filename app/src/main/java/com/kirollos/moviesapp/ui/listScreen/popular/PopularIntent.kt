package com.kirollos.moviesapp.ui.listScreen.popular

sealed class PopularIntent {
    data class GetPopularMovies(val language: String, val page: Int) : PopularIntent()

    data object GetConfigurations : PopularIntent()
}
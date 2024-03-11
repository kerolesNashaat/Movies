package com.kirollos.moviesapp.ui.listScreen.popular

sealed class PopularIntent {
    data class GetPopularMovies(val page: Int) : PopularIntent()
    data class LoadMoreMovies(val page: Int) : PopularIntent()
    data object GetConfigurations : PopularIntent()
}
package com.kirollos.moviesapp.ui.movieDetailScreen

sealed class MovieDetailsIntent {
    data class GetMovieDetails(val language: String, val movieId: Int) : MovieDetailsIntent()
    data object GetConfigurations : MovieDetailsIntent()
}
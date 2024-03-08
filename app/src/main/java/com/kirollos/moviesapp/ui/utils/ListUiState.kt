package com.kirollos.moviesapp.ui.utils

import com.kirollos.network.domain.model.Configurations
import com.kirollos.network.domain.model.Movie
import com.kirollos.network.domain.model.MovieDetail

data class ListUiState(
    val loading: Boolean = false,
    val movies: Movie? = null,
    val config: Configurations? = null,
    val error: String? = null,
    val movieDetail: MovieDetail? = null,
    val movieId: String? = null,
)

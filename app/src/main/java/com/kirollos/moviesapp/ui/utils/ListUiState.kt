package com.kirollos.moviesapp.ui.utils

import androidx.paging.PagingData
import com.kirollos.network.domain.model.Configurations
import com.kirollos.network.domain.model.Movie
import com.kirollos.network.domain.model.MovieDetail
import kotlinx.coroutines.flow.Flow

data class ListUiState(
    val loading: Boolean = false,
    val movies: Movie? = null,
    val config: Configurations? = null,
    val error: String? = null,
    val movieDetail: MovieDetail? = null,
    val movieId: String? = null,
    val moviesFlow: Flow<PagingData<Movie>>? = null,
    val networkAvailability: Boolean? = null
)

package com.kirollos.network.domain.mediator

import com.kirollos.network.data.remote.Resource
import com.kirollos.network.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesMediator {
    suspend fun getMovies(page: Int): Flow<Resource<Movie>>
}
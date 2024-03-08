package com.kirollos.network.domain.repository

import com.kirollos.network.data.remote.Resource
import com.kirollos.network.domain.model.Configurations
import com.kirollos.network.domain.model.Movie
import com.kirollos.network.domain.model.MovieDetail
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getNowPlayingMovies(language: String, page: Int): Flow<Resource<Movie>>
    suspend fun getPopularMovies(language: String, page: Int): Flow<Resource<Movie>>
    suspend fun getUpcomingMovies(language: String, page: Int): Flow<Resource<Movie>>
    suspend fun getMovieDetails(language: String, movieId: Int): Flow<Resource<MovieDetail>>
    suspend fun getConfigurations(): Flow<Resource<Configurations>>
}
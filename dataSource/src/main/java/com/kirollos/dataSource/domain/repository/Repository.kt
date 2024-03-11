package com.kirollos.dataSource.domain.repository

import com.kirollos.dataSource.data.remote.Resource
import com.kirollos.dataSource.domain.model.Configurations
import com.kirollos.dataSource.domain.model.Movie
import com.kirollos.dataSource.domain.model.MovieDetail
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getNowPlayingMovies(page: Int): Flow<Resource<Movie>>
    suspend fun getPopularMovies(page: Int): Flow<Resource<Movie>>
    suspend fun getUpcomingMovies(page: Int): Flow<Resource<Movie>>
    suspend fun getMovieDetails(language: String, movieId: Int): Flow<Resource<MovieDetail>>
    suspend fun getConfigurations(): Flow<Resource<Configurations>>
}
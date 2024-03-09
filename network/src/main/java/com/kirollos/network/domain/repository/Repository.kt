package com.kirollos.network.domain.repository

import androidx.paging.PagingData
import com.kirollos.network.data.local.entity.NowPlayingMovieEntity
import com.kirollos.network.data.remote.Resource
import com.kirollos.network.domain.model.Configurations
import com.kirollos.network.domain.model.Movie
import com.kirollos.network.domain.model.MovieDetail
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getNowPlayingMovies(): Flow<PagingData<Movie>>
    suspend fun getPopularMovies(): Flow<PagingData<Movie>>
    suspend fun getUpcomingMovies(): Flow<PagingData<Movie>>
    suspend fun getMovieDetails(language: String, movieId: Int): Flow<Resource<MovieDetail>>
    suspend fun getConfigurations(): Flow<Resource<Configurations>>
}
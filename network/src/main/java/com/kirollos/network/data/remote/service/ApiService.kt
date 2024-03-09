package com.kirollos.network.data.remote.service

import com.kirollos.network.data.remote.dto.ConfigurationsDto
import com.kirollos.network.data.remote.dto.MovieDetailDto
import com.kirollos.network.data.remote.dto.MovieDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {
    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("language") language: String,
        @Query("page") page: Int
    ): Response<MovieDto>

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("language") language: String,
        @Query("page") page: Int
    ): Response<MovieDto>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("language") language: String,
        @Query("page") page: Int
    ): Response<MovieDto>

    @GET
    suspend fun getMovieDetails(
        @Url url: String,
        @Query("language") language: String
    ): Response<MovieDetailDto>

    @GET("configuration")
    suspend fun getConfigurations(): Response<ConfigurationsDto>

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
    }
}
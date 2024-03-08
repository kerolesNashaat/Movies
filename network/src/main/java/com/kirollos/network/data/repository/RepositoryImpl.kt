package com.kirollos.network.data.repository

import com.google.gson.Gson
import com.kirollos.network.data.remote.Resource
import com.kirollos.network.data.remote.dto.ConfigurationsDto
import com.kirollos.network.data.remote.dto.ErrorResponse
import com.kirollos.network.data.remote.dto.MovieDetailDto
import com.kirollos.network.data.remote.dto.MovieDto
import com.kirollos.network.data.remote.service.ApiService
import com.kirollos.network.data.remote.service.ApiServiceHelper
import com.kirollos.network.domain.model.Configurations
import com.kirollos.network.domain.model.Movie
import com.kirollos.network.domain.model.MovieDetail
import com.kirollos.network.domain.repository.Repository
import com.kirollos.network.uitils.EncryptedSharedPref
import com.kirollos.network.uitils.readTextAndClose
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val apiServiceHelper: ApiServiceHelper,
    private val encryptedPreferences: EncryptedSharedPref
) : Repository {
    override suspend fun getNowPlayingMovies(
        language: String,
        page: Int
    ): Flow<Resource<Movie>> = callbackFlow {
        try {
            val response = apiService.getNowPlayingMovies(language, page)
            if (response.isSuccessful && response.body() != null) {
                trySend(Resource.Success(data = (response.body() as MovieDto).toMovie()))
            } else {
                val errorBody = response.errorBody()
                if (errorBody != null) {
                    val errorString = errorBody.source().inputStream().readTextAndClose()
                    val errorResponse = Gson().fromJson(errorString, ErrorResponse::class.java)
                    trySend(
                        Resource.Failure(
                            code = response.code(),
                            error = errorResponse.statusMessage
                        )
                    )
                } else {
                    trySend(Resource.Failure(error = "Unhandled Error"))
                }
            }
        } catch (ex: java.lang.Exception) {
            trySend(Resource.Failure(error = ex.localizedMessage ?: ""))
        }
        awaitClose()
    }

    override suspend fun getPopularMovies(
        language: String,
        page: Int
    ): Flow<Resource<Movie>> = callbackFlow {
        try {
            val response = apiService.getPopularMovies(language, page)
            if (response.isSuccessful && response.body() != null) {
                trySend(Resource.Success(data = (response.body() as MovieDto).toMovie()))
            } else {
                val errorBody = response.errorBody()
                if (errorBody != null) {
                    val errorString = errorBody.source().inputStream().readTextAndClose()
                    val errorResponse = Gson().fromJson(errorString, ErrorResponse::class.java)
                    trySend(
                        Resource.Failure(
                            code = response.code(),
                            error = errorResponse.statusMessage
                        )
                    )
                } else {
                    trySend(Resource.Failure(error = "Unhandled Error"))
                }
            }
        } catch (ex: java.lang.Exception) {
            trySend(Resource.Failure(error = ex.localizedMessage ?: ""))
        }
        awaitClose()
    }

    override suspend fun getUpcomingMovies(
        language: String,
        page: Int
    ): Flow<Resource<Movie>> = callbackFlow {
        try {
            val response = apiService.getUpcomingMovies(language, page)
            if (response.isSuccessful && response.body() != null) {
                trySend(Resource.Success(data = (response.body() as MovieDto).toMovie()))
            } else {
                val errorBody = response.errorBody()
                if (errorBody != null) {
                    val errorString = errorBody.source().inputStream().readTextAndClose()
                    val errorResponse = Gson().fromJson(errorString, ErrorResponse::class.java)
                    trySend(
                        Resource.Failure(
                            code = response.code(),
                            error = errorResponse.statusMessage
                        )
                    )
                } else {
                    trySend(Resource.Failure(error = "Unhandled Error"))
                }
            }
        } catch (ex: java.lang.Exception) {
            trySend(Resource.Failure(error = ex.localizedMessage ?: ""))
        }
        awaitClose()
    }

    override suspend fun getMovieDetails(
        language: String,
        movieId: Int
    ): Flow<Resource<MovieDetail>> = callbackFlow {
        try {
            val response = apiService.getMovieDetails(
                apiServiceHelper.getMovieDetailsUrl(movieId), language
            )
            if (response.isSuccessful && response.body() != null) {
                trySend(Resource.Success(data = (response.body() as MovieDetailDto).toMovieDetail()))
            } else {
                val errorBody = response.errorBody()
                if (errorBody != null) {
                    val errorString = errorBody.source().inputStream().readTextAndClose()
                    val errorResponse = Gson().fromJson(errorString, ErrorResponse::class.java)
                    trySend(
                        Resource.Failure(
                            code = response.code(),
                            error = errorResponse.statusMessage
                        )
                    )
                } else {
                    trySend(Resource.Failure(error = "Unhandled Error"))
                }
            }
        } catch (ex: java.lang.Exception) {
            trySend(Resource.Failure(error = ex.localizedMessage ?: ""))
        }
        awaitClose()
    }

    override suspend fun getConfigurations(): Flow<Resource<Configurations>> =
        callbackFlow {
            val configEntity = encryptedPreferences.getConfig()
            if (configEntity != null) {
                delay(200)
                trySend(Resource.Success(data = configEntity))
            } else {
                try {
                    val response = apiService.getConfigurations()
                    if (response.isSuccessful && response.body() != null) {
                        val entity = (response.body() as ConfigurationsDto)
                        encryptedPreferences.insertConfig(entity)
                        trySend(Resource.Success(data = entity.toConfigurations()))
                    } else {
                        val errorBody = response.errorBody()
                        if (errorBody != null) {
                            val errorString = errorBody.source().inputStream().readTextAndClose()
                            val errorResponse =
                                Gson().fromJson(errorString, ErrorResponse::class.java)
                            trySend(
                                Resource.Failure(
                                    code = response.code(),
                                    error = errorResponse.statusMessage
                                )
                            )
                        } else {
                            trySend(Resource.Failure(error = "Unhandled Error"))
                        }
                    }
                } catch (ex: java.lang.Exception) {
                    trySend(Resource.Failure(error = ex.localizedMessage ?: ""))
                }
            }

            awaitClose()
        }

}
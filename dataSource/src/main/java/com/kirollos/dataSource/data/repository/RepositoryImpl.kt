package com.kirollos.dataSource.data.repository

import com.google.gson.Gson
import com.kirollos.dataSource.data.mediator.NowPlayingMediator
import com.kirollos.dataSource.data.mediator.PopularMediator
import com.kirollos.dataSource.data.mediator.UpcomingMediator
import com.kirollos.dataSource.data.remote.Resource
import com.kirollos.dataSource.data.remote.dto.ConfigurationsDto
import com.kirollos.dataSource.data.remote.dto.ErrorResponse
import com.kirollos.dataSource.data.remote.dto.MovieDetailDto
import com.kirollos.dataSource.data.remote.service.ApiService
import com.kirollos.dataSource.data.remote.service.ApiServiceHelper
import com.kirollos.dataSource.domain.model.Configurations
import com.kirollos.dataSource.domain.model.Movie
import com.kirollos.dataSource.domain.model.MovieDetail
import com.kirollos.dataSource.domain.repository.Repository
import com.kirollos.dataSource.uitils.EncryptedSharedPref
import com.kirollos.dataSource.uitils.readTextAndClose
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val apiServiceHelper: ApiServiceHelper,
    private val encryptedPreferences: EncryptedSharedPref,
    private val nowPlayingMediator: NowPlayingMediator,
    private val popularMediator: PopularMediator,
    private val upcomingMediator: UpcomingMediator
) : Repository {
    override suspend fun getNowPlayingMovies(page: Int): Flow<Resource<Movie>> =
        nowPlayingMediator.getMovies(page)

    override suspend fun getPopularMovies(page: Int): Flow<Resource<Movie>> =
        popularMediator.getMovies(page)

    override suspend fun getUpcomingMovies(page: Int): Flow<Resource<Movie>> =
        upcomingMediator.getMovies(page)

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
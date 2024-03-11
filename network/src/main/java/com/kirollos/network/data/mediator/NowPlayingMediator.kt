package com.kirollos.network.data.mediator

import android.database.sqlite.SQLiteException
import com.google.gson.Gson
import com.kirollos.network.data.local.MoviesDatabase
import com.kirollos.network.data.local.entity.NowPlayingMovieEntity
import com.kirollos.network.data.remote.Resource
import com.kirollos.network.data.remote.dto.ErrorResponse
import com.kirollos.network.data.remote.dto.MovieDto
import com.kirollos.network.data.remote.service.ApiService
import com.kirollos.network.data.toMovie
import com.kirollos.network.data.toNowPlayingMovieEntity
import com.kirollos.network.domain.model.Movie
import com.kirollos.network.uitils.UNHANDLED_ERROR
import com.kirollos.network.uitils.language
import com.kirollos.network.uitils.readTextAndClose
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

class NowPlayingMediator @Inject constructor(
    private val apiService: ApiService, private val moviesDatabase: MoviesDatabase
) {
    suspend fun getMovies(page: Int): Flow<Resource<Movie>> =
        callbackFlow {
            try {
                getCachedMovies(page).also { cashedMovies ->
                    when (cashedMovies) {
                        is Resource.Failure -> {
                            getRemoteMoviesAndCash(language, page).also { res ->
                                when (res) {
                                    is Resource.Failure ->
                                        trySend(Resource.Failure(code = null, error = res.error))

                                    is Resource.Success -> trySend(Resource.Success(data = res.data))
                                }
                            }
                        }

                        is Resource.Success -> trySend(Resource.Success(data = cashedMovies.data))
                    }
                }
            } catch (ex: Exception) {
                trySend(Resource.Failure(code = null, error = handleException(ex)))
            }
            awaitClose()
        }

    private suspend fun getCachedMovies(page: Int): Resource<Movie> {
        return try {
            val movieEntity = moviesDatabase.moviesDao().getNowPlayingMovie(page)
            if (movieEntity == null) Resource.Failure(code = null, error = null)
            else Resource.Success(data = movieEntity.toMovie())
        } catch (ex: SQLiteException) {
            Resource.Failure(code = null, error = handleException(ex))
        }
    }

    private suspend fun getRemoteMoviesAndCash(
        language: String, page: Int
    ): Resource<Movie> {
        return try {
            val response = apiService.getNowPlayingMovies(language, page)

            if (response.isSuccessful && response.body() != null) {
                val movieEntity = response.body()!!.toNowPlayingMovieEntity()
                cashMovies(movieEntity).also { res ->
                    when (res) {
                        is Resource.Failure -> Resource.Failure(
                            code = response.code(),
                            error = res.error
                        )

                        is Resource.Success -> Resource.Success(data = res.data)
                    }
                }
            } else {
                val errorMessage = handleApiFailure(response)
                Resource.Failure(code = response.code(), error = errorMessage)
            }
        } catch (ex: HttpException) {
            Resource.Failure(code = null, error = handleException(ex))
        }

    }

    private suspend fun cashMovies(movieEntity: NowPlayingMovieEntity): Resource<Movie> {
        return try {
            val res = moviesDatabase.moviesDao().insertNowPlayingMovie(movieEntity)
            if (res != -1L) Resource.Success(data = movieEntity.toMovie())
            else Resource.Failure(code = null, error = null)
        } catch (ex: SQLiteException) {
            Resource.Failure(code = null, error = handleException(ex))
        }
    }

    private fun handleException(ex: Exception): String {
        ex.printStackTrace()
        return ex.localizedMessage ?: UNHANDLED_ERROR
    }

    private fun handleApiFailure(response: Response<MovieDto>): String? {
        return try {
            val errorBody = response.errorBody()
            if (errorBody != null) {
                val errorString = errorBody.source().inputStream().readTextAndClose()
                val errorResponse = Gson().fromJson(errorString, ErrorResponse::class.java)
                errorResponse.statusMessage
            } else UNHANDLED_ERROR
        } catch (ex: IOException) {
            handleException(ex)
        }
    }
}
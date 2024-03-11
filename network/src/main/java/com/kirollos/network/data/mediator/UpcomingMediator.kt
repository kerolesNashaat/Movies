package com.kirollos.network.data.mediator

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.kirollos.network.data.local.MoviesDatabase
import com.kirollos.network.data.local.entity.UpcomingMovieEntity
import com.kirollos.network.data.remote.service.ApiService
import com.kirollos.network.data.toUpcomingMovieEntity
import com.kirollos.network.uitils.checkNetworkAvailability
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class UpcomingMediator @Inject constructor(
    private val language: String,
    private val moviesDatabase: MoviesDatabase,
    private val apiService: ApiService,
    @ApplicationContext private val context: Context,
) : RemoteMediator<Int, UpcomingMovieEntity>() {

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.SKIP_INITIAL_REFRESH
    }
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, UpcomingMovieEntity>
    ): MediatorResult {
        return try {
            val nextPage: Int = when (loadType) {
                LoadType.REFRESH -> { 1 }
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) 1
                    else {
                        val nextPage = (lastItem.page ?: 1) + 1
                        val totalPages = lastItem.totalPages ?: 1
                        if (nextPage > totalPages)
                            return MediatorResult.Success(endOfPaginationReached = true)
                        else nextPage
                    }
                }
            }

            if (checkNetworkAvailability(context)) {
                val res = apiService.getUpcomingMovies(language = language, page = nextPage)
                if (res.isSuccessful) {
                    moviesDatabase.moviesDao()
                        .insertUpcomingMovie(res.body()?.toUpcomingMovieEntity()!!)
                }
                MediatorResult.Success(endOfPaginationReached = (res.body() == null))
            } else MediatorResult.Success(endOfPaginationReached = false)

        } catch (ex: IOException) {
            MediatorResult.Error(ex)
        } catch (ex: HttpException) {
            MediatorResult.Error(ex)
        }
    }
}
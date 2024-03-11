package com.kirollos.dataSource.di

import com.kirollos.dataSource.data.local.MoviesDatabase
import com.kirollos.dataSource.data.mediator.NowPlayingMediator
import com.kirollos.dataSource.data.mediator.PopularMediator
import com.kirollos.dataSource.data.mediator.UpcomingMediator
import com.kirollos.dataSource.data.remote.service.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MediatorModule {
    @Provides
    @Singleton
    fun provideNowPlayingMediator(
        apiService: ApiService, moviesDatabase: MoviesDatabase
    ) = NowPlayingMediator(apiService, moviesDatabase)

    @Provides
    @Singleton
    fun providePopularMediator(
        apiService: ApiService, moviesDatabase: MoviesDatabase
    ) = PopularMediator(apiService, moviesDatabase)

    @Provides
    @Singleton
    fun provideUpcomingMediator(
        apiService: ApiService, moviesDatabase: MoviesDatabase
    ) = UpcomingMediator(apiService, moviesDatabase)

}
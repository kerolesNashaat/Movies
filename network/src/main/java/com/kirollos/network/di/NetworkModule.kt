package com.kirollos.network.di

import android.content.Context
import androidx.compose.ui.text.intl.Locale
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.kirollos.network.data.local.MoviesDatabase
import com.kirollos.network.data.local.entity.NowPlayingMovieEntity
import com.kirollos.network.data.local.entity.PopularMovieEntity
import com.kirollos.network.data.local.entity.UpcomingMovieEntity
import com.kirollos.network.data.mediator.NowPlayingMediator
import com.kirollos.network.data.mediator.PopularMediator
import com.kirollos.network.data.mediator.UpcomingMediator
import com.kirollos.network.data.remote.service.ApiService
import com.kirollos.network.data.remote.service.ApiService.Companion.BASE_URL
import com.kirollos.network.data.remote.service.AuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Singleton
    @Provides
    fun providesOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(authInterceptor)
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
}
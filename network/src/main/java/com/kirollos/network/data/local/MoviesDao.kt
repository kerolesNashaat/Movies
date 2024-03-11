package com.kirollos.network.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.kirollos.network.data.local.entity.NowPlayingMovieEntity
import com.kirollos.network.data.local.entity.PopularMovieEntity
import com.kirollos.network.data.local.entity.UpcomingMovieEntity
import com.kirollos.network.uitils.TABLE_NOW_PLAYING_MOVIE
import com.kirollos.network.uitils.TABLE_POPULAR_MOVIE
import com.kirollos.network.uitils.TABLE_UPCOMING_MOVIE

@Dao
interface MoviesDao {
    //Now Playing Table//
    @Query("SELECT * FROM $TABLE_NOW_PLAYING_MOVIE WHERE page = :page")
    suspend fun getNowPlayingMovie(page: Int): NowPlayingMovieEntity

    @Query("SELECT * FROM $TABLE_POPULAR_MOVIE WHERE page = :page")
    suspend fun getPopularMovie(page: Int): PopularMovieEntity

    @Query("SELECT * FROM $TABLE_UPCOMING_MOVIE WHERE page = :page")
    suspend fun getUpcomingMovie(page: Int): UpcomingMovieEntity

    @Upsert
    suspend fun insertNowPlayingMovie(movie: NowPlayingMovieEntity): Long

    @Upsert
    suspend fun insertPopularMovie(movie: PopularMovieEntity): Long

    @Upsert
    suspend fun insertUpcomingMovie(movie: UpcomingMovieEntity): Long

}
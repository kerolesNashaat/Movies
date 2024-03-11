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
    @Upsert
    suspend fun insertNowPlayingMovie(movie: NowPlayingMovieEntity): Long

    @Query("SELECT * FROM $TABLE_NOW_PLAYING_MOVIE WHERE page = :page")
    suspend fun getNowPlayingMovie(page: Int): NowPlayingMovieEntity

    @Query("DELETE FROM $TABLE_NOW_PLAYING_MOVIE")
    suspend fun deleteNowPlayingMovies()

    //Popular Table//
    @Upsert
    suspend fun insertPopularMovie(movie: PopularMovieEntity): Long

    @Query("SELECT * FROM $TABLE_POPULAR_MOVIE")
    suspend fun getPopularMovie(): PopularMovieEntity

    @Query("DELETE FROM $TABLE_POPULAR_MOVIE")
    suspend fun deletePopularMovies()

    //Upcoming Table//
    @Upsert
    suspend fun insertUpcomingMovie(movie: UpcomingMovieEntity): Long

    @Query("SELECT * FROM $TABLE_UPCOMING_MOVIE")
    suspend fun getUpcomingMovie(): UpcomingMovieEntity

    @Query("DELETE FROM $TABLE_UPCOMING_MOVIE")
    suspend fun deleteUpcomingMovies()
}
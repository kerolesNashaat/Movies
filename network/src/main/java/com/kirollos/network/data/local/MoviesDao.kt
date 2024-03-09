package com.kirollos.network.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
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
    @Insert
    suspend fun insertNowPlayingMovie(movie: NowPlayingMovieEntity)

    @Query("SELECT * FROM $TABLE_NOW_PLAYING_MOVIE")
    fun getNowPlayingMovie(): PagingSource<Int, NowPlayingMovieEntity>

    @Query("DELETE FROM $TABLE_NOW_PLAYING_MOVIE")
    suspend fun deleteNowPlayingMovies()

    //Popular Table//
    @Insert
    suspend fun insertPopularMovie(movie: PopularMovieEntity)

    @Query("SELECT * FROM $TABLE_POPULAR_MOVIE")
    fun getPopularMovie(): PagingSource<Int, PopularMovieEntity>

    @Query("DELETE FROM $TABLE_POPULAR_MOVIE")
    suspend fun deletePopularMovies()

    //Upcoming Table//
    @Insert
    suspend fun insertUpcomingMovie(movie: UpcomingMovieEntity)

    @Query("SELECT * FROM $TABLE_UPCOMING_MOVIE")
    fun getUpcomingMovie(): PagingSource<Int, UpcomingMovieEntity>

    @Query("DELETE FROM $TABLE_UPCOMING_MOVIE")
    suspend fun deleteUpcomingMovies()
}
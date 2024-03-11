package com.kirollos.dataSource.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kirollos.dataSource.data.local.entity.NowPlayingMovieEntity
import com.kirollos.dataSource.data.local.entity.PopularMovieEntity
import com.kirollos.dataSource.data.local.entity.UpcomingMovieEntity

@Database(
    entities = [NowPlayingMovieEntity::class,
        PopularMovieEntity::class,
        UpcomingMovieEntity::class], version = 1
)
@TypeConverters(ListTypeConverter::class)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
}
package com.kirollos.dataSource.data.local.entity

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.kirollos.dataSource.data.local.ListTypeConverter
import com.kirollos.dataSource.data.remote.dto.DatesDto
import com.kirollos.dataSource.domain.model.Result
import com.kirollos.dataSource.uitils.TABLE_NOW_PLAYING_MOVIE
import javax.annotation.Nonnull

@Entity(tableName = TABLE_NOW_PLAYING_MOVIE)
data class NowPlayingMovieEntity(
    @PrimaryKey(autoGenerate = true)
    @Nonnull
    var id: Long,
    @Ignore var datesDto: DatesDto? = null,
    var page: Int? = null,
    @TypeConverters(ListTypeConverter::class)
    var resultList: MutableList<Result?>? = null,
    var totalPages: Int? = null,
    @Ignore var totalResults: Int? = null
) {
    constructor(id: Long) : this(
        id = id,
        datesDto = null,
        page = null,
        resultList = null,
        totalPages = null,
        totalResults = null,
    )
}

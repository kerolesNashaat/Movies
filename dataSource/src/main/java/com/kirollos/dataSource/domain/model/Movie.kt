package com.kirollos.dataSource.domain.model

import com.kirollos.dataSource.data.remote.dto.DatesDto

data class Movie(
    val id: Long,
    val datesDto: DatesDto? = null,
    val page: Int? = null,
    var resultList: MutableList<Result?>? = null,
    val totalPages: Int? = null,
    val totalResults: Int? = null
)

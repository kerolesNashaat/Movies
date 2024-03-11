package com.kirollos.network.domain.model

import com.kirollos.network.data.remote.dto.DatesDto

data class Movie(
    val id: Long,
    val datesDto: DatesDto? = null,
    val page: Int? = null,
    var resultList: MutableList<Result?>? = null,
    val totalPages: Int? = null,
    val totalResults: Int? = null
)

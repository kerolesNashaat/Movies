package com.kirollos.network.domain.model

import com.kirollos.network.data.remote.dto.DatesDto

data class Movie(
    val datesDto: DatesDto? = null,
    val page: Int? = null,
    val resultList: List<Result?>? = null,
    val totalPages: Int? = null,
    val totalResults: Int? = null
)

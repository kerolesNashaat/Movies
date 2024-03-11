package com.kirollos.dataSource.domain.model

import com.kirollos.dataSource.data.remote.dto.Genre

data class MovieDetail(
    val genres: List<Genre?>? = null,
    val id: Int? = null,
    val overview: String? = null,
    val posterPath: String? = null,
)

package com.kirollos.network.domain.model

import com.kirollos.network.data.remote.dto.BelongsToCollection
import com.kirollos.network.data.remote.dto.Genre
import com.kirollos.network.data.remote.dto.ProductionCompany
import com.kirollos.network.data.remote.dto.ProductionCountry
import com.kirollos.network.data.remote.dto.SpokenLanguage

data class MovieDetail(
    val genres: List<Genre?>? = null,
    val id: Int? = null,
    val overview: String? = null,
    val posterPath: String? = null,
)

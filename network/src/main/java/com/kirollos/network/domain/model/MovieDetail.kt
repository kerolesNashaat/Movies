package com.kirollos.network.domain.model

import com.kirollos.network.data.remote.dto.BelongsToCollection
import com.kirollos.network.data.remote.dto.Genre
import com.kirollos.network.data.remote.dto.ProductionCompany
import com.kirollos.network.data.remote.dto.ProductionCountry
import com.kirollos.network.data.remote.dto.SpokenLanguage

data class MovieDetail(
    val adult: Boolean? = null,
    val backdropPath: String? = null,
    val belongsToCollection: BelongsToCollection? = null,
    val budget: Int? = null,
    val genres: List<Genre?>? = null,
    val homepage: String? = null,
    val id: Int? = null,
    val imdbId: String? = null,
    val originalLanguage: String? = null,
    val originalTitle: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    val posterPath: String? = null,
    val productionCompanies: List<ProductionCompany?>? = null,
    val productionCountries: List<ProductionCountry?>? = null,
    val releaseDate: String? = null,
    val revenue: Int? = null,
    val runtime: Int? = null,
    val spokenLanguages: List<SpokenLanguage?>? = null,
    val status: String? = null,
    val tagline: String? = null,
    val title: String? = null,
    val video: Boolean? = null,
    val voteAverage: Double? = null,
    val voteCount: Int? = null,
)
package com.kirollos.network.data.remote.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.kirollos.network.domain.model.Movie
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieDto(
    @SerializedName("dates")
    val datesDto: DatesDto? = null,
    @SerializedName("page")
    val page: Int? = null,
    @SerializedName("results")
    val resultEntities: List<ResultDto?>? = null,
    @SerializedName("total_pages")
    val totalPages: Int? = null,
    @SerializedName("total_results")
    val totalResults: Int? = null
) : Parcelable {
    fun toMovie(): Movie {
        val resultList = resultEntities?.map { it?.toResult() }
        return Movie(datesDto, page, resultList, totalPages, totalResults)
    }

}
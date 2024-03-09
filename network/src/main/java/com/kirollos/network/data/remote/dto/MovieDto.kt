package com.kirollos.network.data.remote.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieDto(
    @SerializedName("dates")
    val datesDto: DatesDto? = null,
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val resultEntities: List<ResultDto?>? = null,
    @SerializedName("total_pages")
    val totalPages: Int? = null,
    @SerializedName("total_results")
    val totalResults: Int? = null
) : Parcelable
package com.kirollos.network.data.remote.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DatesDto(
    @SerializedName("maximum")
    val maximum: String? = null,
    @SerializedName("minimum")
    val minimum: String? = null
) : Parcelable
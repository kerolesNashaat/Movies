package com.kirollos.network.data.remote.dto


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class GenreDto(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null
) : Parcelable
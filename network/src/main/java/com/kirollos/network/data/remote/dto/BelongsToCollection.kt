package com.kirollos.network.data.remote.dto


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class BelongsToCollection(
    @SerializedName("backdrop_path")
    val backdropPath: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("poster_path")
    val posterPath: String? = null
) : Parcelable
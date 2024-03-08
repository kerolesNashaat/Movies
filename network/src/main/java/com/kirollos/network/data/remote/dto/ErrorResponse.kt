package com.kirollos.network.data.remote.dto


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class ErrorResponse(
    @SerializedName("status_code")
    val statusCode: Int? = null,
    @SerializedName("status_message")
    val statusMessage: String? = null,
    @SerializedName("success")
    val success: Boolean? = null
) : Parcelable
package com.kirollos.network.data.remote.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.kirollos.network.domain.model.Configurations
import kotlinx.parcelize.Parcelize

@Parcelize
data class ConfigurationsDto(
    @SerializedName("change_keys")
    val changeKeys: List<String?>? = null,
    @SerializedName("images")
    val images: Images? = null
) : Parcelable {
    fun toConfigurations() = Configurations(
        changeKeys, images
    )
}


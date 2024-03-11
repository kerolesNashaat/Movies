package com.kirollos.dataSource.data.remote.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.kirollos.dataSource.domain.model.Configurations
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


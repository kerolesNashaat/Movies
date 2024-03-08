package com.kirollos.network.domain.model

import com.kirollos.network.data.remote.dto.Images

data class Configurations(
    val changeKeys: List<String?>? = null,
    val images: Images? = null
)

package com.kirollos.dataSource.domain.model

import com.kirollos.dataSource.data.remote.dto.Images

data class Configurations(
    val changeKeys: List<String?>? = null,
    val images: Images? = null
)

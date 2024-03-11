package com.kirollos.dataSource.uitils

import android.content.SharedPreferences
import com.google.gson.Gson
import com.kirollos.dataSource.data.remote.dto.ConfigurationsDto
import com.kirollos.dataSource.domain.model.Configurations
import javax.inject.Inject

class EncryptedSharedPref @Inject constructor(
    private val encryptedPreferences: SharedPreferences
) {

    fun insertConfig(configEntity: ConfigurationsDto) {
        encryptedPreferences.edit().apply {
            putString(CONFIG_KEY, Gson().toJson(configEntity))
        }.apply()
    }

    fun getConfig(): Configurations? {
        return Gson().fromJson(
            encryptedPreferences.getString(CONFIG_KEY, ""),
            ConfigurationsDto::class.java
        )?.toConfigurations()
    }
}
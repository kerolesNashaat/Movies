package com.kirollos.dataSource.data.remote.service

import com.kirollos.dataSource.data.remote.service.ApiService.Companion.BASE_URL
import javax.inject.Inject

class ApiServiceHelper @Inject constructor() {
    fun getMovieDetailsUrl(movieId: Int): String {
        return "${BASE_URL}movie/$movieId"
    }
}
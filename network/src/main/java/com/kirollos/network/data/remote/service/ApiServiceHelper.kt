package com.kirollos.network.data.remote.service

import com.kirollos.network.uitils.BASE_URL
import javax.inject.Inject

class ApiServiceHelper @Inject constructor() {
    fun getMovieDetailsUrl(movieId: Int): String {
        return "${BASE_URL}movie/$movieId"
    }
}
package com.kirollos.network.data.remote

sealed class Resource<T> {
    data class Success<T>(val data: T) : Resource<T>()
    data class Failure<T>(val code: Int? = null, val error: String? = null) :
        Resource<T>()
}
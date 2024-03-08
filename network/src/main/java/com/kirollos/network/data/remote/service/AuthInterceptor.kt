package com.kirollos.network.data.remote.service

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url: HttpUrl = request.url().newBuilder()
            .addQueryParameter("api_key", "22d1f1c476bae9da5f6e291f331b65c1")
            .build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}
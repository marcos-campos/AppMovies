package com.example.pagmovies.network

import com.example.pagmovies.repository.RepositoryApi
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class ApiKeyInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        val originalRequest: Request = chain.request()
        val originalUrl: HttpUrl = originalRequest.url
        val newUrl: HttpUrl = originalUrl.newBuilder()
            .addQueryParameter("api_key", RepositoryApi.chave)
            .build()
        val requestBuilder: Request.Builder = originalRequest.newBuilder()
            .url(newUrl)
        val request: Request = requestBuilder.build()

        return chain.proceed(request)

    }

}
package com.balinasoft.themoviedb.data.api.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class QueryParamsInterceptor(private val values: Map<String, String>) :
    Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url()
        val builder = originalHttpUrl.newBuilder()
        for ((key, value) in values) {
            builder.addQueryParameter(key, value)
        }
        val url = builder.build()
        // Request customization: add request headers
        val requestBuilder = original.newBuilder()
            .url(url)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }

}
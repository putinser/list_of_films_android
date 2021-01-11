package com.vmeste.app.data.api.interceptors

/*
class TokenInterceptor : Interceptor {

    private val preferencesRepository = DaggerUtils.appComponent.providePreferencesRepository()

    val VERSION_HEADER = "Version"
    val PLATFORM_HEADER = "Platform"

    override fun intercept(chain: Interceptor.Chain): Response {

        val original = chain.request()
        val originalHttpUrl = original.url()

        val url = originalHttpUrl.newBuilder()
            .build()

        // Request customization: add request headers
        val requestBuilder = original.newBuilder()
            .url(url)

        val token = preferencesRepository.getToken()

        if (token != "")
            requestBuilder.addHeader("Access-Token", token)

        requestBuilder.addHeader(PLATFORM_HEADER, "Android")
        requestBuilder.addHeader(VERSION_HEADER, BuildConfig.VERSION_CODE.toString())

        val request = requestBuilder.build()
        return chain.proceed(request)

    }
}
*/

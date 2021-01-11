package com.balinasoft.themoviedb.data.api

import com.balinasoft.themoviedb.BuildConfig
import com.google.gson.GsonBuilder


import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit.SECONDS

class RetrofitUtils private constructor() {

    companion object {
        val instance: RetrofitUtils by lazy {
            RetrofitUtils()
        }
    }

    val retrofitDefault: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("BuildConfig.SERVER_URL" + "/")
            .addConverterFactory(GsonConverterFactory.create(buildGson()))
            .client(buildHttpClient())
            .build()
    }

    private fun buildHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()



        if (BuildConfig.DEBUG) {
            builder.addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }

        builder.connectTimeout(30, SECONDS)
        builder.writeTimeout(30, SECONDS)
        builder.readTimeout(60, SECONDS)

        return builder.build()
    }

    private fun buildGson() = GsonBuilder()
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
        .create()
}

fun <T> Call<T>.bodyOrError(): T {
    return this.execute().bodyOrError()
}

fun <T> Response<T>.bodyOrError(): T {
    if (this.isSuccessful) {
        return this.body()!!
    }

    throw HttpException(this)
}

fun <T> Call<T>.isSuccessfulOrError(): Boolean {
    return this.execute().isSuccessfulOrError()
}

fun <T> Response<T>.isSuccessfulOrError(): Boolean {
    if (this.isSuccessful) {
        return true
    }

    throw HttpException(this)
}
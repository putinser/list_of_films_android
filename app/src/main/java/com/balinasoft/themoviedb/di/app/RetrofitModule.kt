package com.balinasoft.themoviedb.di.app

import com.balinasoft.themoviedb.BuildConfig

import com.balinasoft.themoviedb.data.api.interceptors.QueryParamsInterceptor
import com.balinasoft.themoviedb.data.api.interfaces.MoviesApi
import com.balinasoft.themoviedb.data.repository.movies_repository.IMoviesRepository
import com.balinasoft.themoviedb.data.repository.movies_repository.MoviesRepository
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import javax.inject.Singleton

@Module(includes = [AppModule::class])
class RetrofitModule {

    @Singleton
    @Provides
    fun provideMoviesRepository(retrofit: Retrofit): IMoviesRepository {
        val api = retrofit.create<MoviesApi>(MoviesApi::class.java)

        return MoviesRepository(api)
    }

    /*@Singleton
    @Provides
    fun getMoviesDatabaseApi(
        retrofit: Retrofit
    ): MoviesApi {
        return retrofit.create<MoviesApi>(MoviesApi::class.java)
    }
*/
    @Singleton
    @Provides
    fun getRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_API_URL)
            .addConverterFactory(GsonConverterFactory.create(buildGson()))
            .build()
    }

    private fun buildGson() = GsonBuilder()
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
        .create()


    @Singleton
    @Provides
    fun okHttpClient(): OkHttpClient {

        val builder = OkHttpClient()
            .newBuilder()

        if (BuildConfig.DEBUG) {
            builder.addInterceptor(HttpLoggingInterceptor().also {
                it.level = HttpLoggingInterceptor.Level.BODY
            })
        }

        val apiKey = BuildConfig.API_KEY
        val params: MutableMap<String, String> =
            HashMap()
        params["api_key"] = apiKey
        builder.addInterceptor(QueryParamsInterceptor(params))
        return builder.build()
    }
}
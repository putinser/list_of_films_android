package com.balinasoft.themoviedb.di

import android.content.Context
import com.balinasoft.themoviedb.di.app.AppComponent
import com.balinasoft.themoviedb.di.app.DaggerAppComponent
import com.balinasoft.themoviedb.di.app.RepositoryModule

object DaggerUtils {

    lateinit var appComponent: AppComponent


    @JvmStatic
    fun buildComponents(context: Context) {
        appComponent = buildAppComponent(context)
    }

    private fun buildAppComponent(context: Context) = DaggerAppComponent.builder()
        .repositoryModule(RepositoryModule(context))
        .build()
}
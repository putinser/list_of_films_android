package com.balinasoft.themoviedb

import android.app.Application
import android.content.Context
import com.balinasoft.themoviedb.di.DaggerUtils

class App() : Application() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        DaggerUtils.buildComponents(this)
    }
}

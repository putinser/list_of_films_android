package com.balinasoft.themoviedb.di.app

import android.content.Context
import com.balinasoft.themoviedb.data.string.StringProvider
import com.balinasoft.themoviedb.data.string.StringProviderImp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [AppModule::class])
class RepositoryModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideStringProvider(): StringProvider =
        StringProviderImp(context)

}
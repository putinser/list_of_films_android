package com.balinasoft.themoviedb.di.app

import com.balinasoft.themoviedb.data.repository.movies_repository.IMoviesRepository
import com.balinasoft.themoviedb.data.string.StringProvider
import com.balinasoft.themoviedb.mvp.movies_list.MoviesListPresenter
import com.balinasoft.themoviedb.mvp.movies_list.PopularListInteractor
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        RepositoryModule::class,
        RetrofitModule::class]
)
interface AppComponent {

    fun provideStringProvider(): StringProvider
    fun provideMoviesRepository(): IMoviesRepository

    fun inject(o: MoviesListPresenter)
    fun inject(o: PopularListInteractor)


}
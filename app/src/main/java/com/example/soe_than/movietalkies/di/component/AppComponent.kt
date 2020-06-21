package com.example.soe_than.movietalkies.di.component

import android.app.Application
import com.example.soe_than.movietalkies.MovieApp
import com.example.soe_than.movietalkies.data.repository.MoviesRepository
import com.example.soe_than.movietalkies.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
        modules = [AndroidInjectionModule::class,
            ActivityModule::class,
            ViewModelModule::class,
            ApiModule::class,
            DbModule::class]
)
interface AppComponent {


    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }



    fun inject(app: MovieApp)
}
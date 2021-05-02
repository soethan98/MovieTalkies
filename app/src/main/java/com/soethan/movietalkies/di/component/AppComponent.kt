package com.soethan.movietalkies.di.component

import android.app.Application
import com.soethan.movietalkies.MovieApp
import com.soethan.movietalkies.di.module.*
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
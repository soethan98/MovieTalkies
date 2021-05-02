package com.soethan.movietalkies

import android.app.Application
import com.soethan.movietalkies.di.AppInjector
import com.soethan.movietalkies.di.component.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MovieApp : Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>
    override fun onCreate() {
        super.onCreate()

        AppInjector.init(this)

    }


    override fun androidInjector() = dispatchingAndroidInjector
}
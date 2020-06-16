package com.example.soe_than.movietalkies

import android.app.Activity
import android.app.Application
import com.example.soe_than.movietalkies.di.AppInjector
import com.example.soe_than.movietalkies.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
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
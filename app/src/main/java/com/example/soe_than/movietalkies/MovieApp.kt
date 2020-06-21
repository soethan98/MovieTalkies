package com.example.soe_than.movietalkies

import android.app.Application
import androidx.lifecycle.LifecycleObserver
import com.example.soe_than.movietalkies.di.AppInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MovieApp : Application(), HasAndroidInjector, LifecycleObserver {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>
    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)
    }

    override fun androidInjector() = dispatchingAndroidInjector
}

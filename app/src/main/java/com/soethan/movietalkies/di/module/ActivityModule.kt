package com.soethan.movietalkies.di.module

import com.soethan.movietalkies.MainActivity
import com.soethan.movietalkies.ui.detail.DetailActivity
import com.soethan.movietalkies.ui.detail.SearchDetailActivity
import com.soethan.movietalkies.ui.search.SearchActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector()
    abstract fun contributeDetailActivity(): DetailActivity

    @ContributesAndroidInjector()
    abstract fun contributeSearchActivity(): SearchActivity


    @ContributesAndroidInjector()
    abstract fun contributeSearchDetailActivity(): SearchDetailActivity




}
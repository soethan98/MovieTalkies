package com.example.soe_than.movietalkies.di.module

import com.example.soe_than.movietalkies.MainActivity
import com.example.soe_than.movietalkies.ui.detail.DetailActivity
import com.example.soe_than.movietalkies.ui.detail.SearchDetailActivity
import com.example.soe_than.movietalkies.ui.search.SearchActivity
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
package com.example.soe_than.movietalkies.di.module

import com.example.soe_than.movietalkies.ui.fragment.*
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {


    @ContributesAndroidInjector
    abstract fun contributeNowShowingFragment(): NowShowingFragment

    @ContributesAndroidInjector
    abstract fun contributePopularFragment(): PopularFragment

    @ContributesAndroidInjector
    abstract fun contributeTopRatedFragment(): TopRatedFragment

    @ContributesAndroidInjector
    abstract fun contributeUpComingFragment(): UpcomingFragment

    @ContributesAndroidInjector
    abstract fun contributeFavouriteFragment(): FavouriteFragment

}
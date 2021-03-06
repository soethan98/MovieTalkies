package com.soethan.movietalkies.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.soethan.movietalkies.ui.ViewModel.DetailViewModel
import com.soethan.movietalkies.ui.ViewModel.FavouriteViewModel
import com.soethan.movietalkies.ui.ViewModel.MovieViewModel
import com.soethan.movietalkies.ui.ViewModel.SearchDetailViewModel
import com.soethan.movietalkies.ui.ViewModelFactory.MainViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ViewModelModule {


    @Binds
    abstract fun bindViewModelFactory(factory: MainViewModelFactory): ViewModelProvider.Factory


    @Binds
    @IntoMap
    @ViewModelKey(MovieViewModel::class)
    abstract fun bindMovieViewModel(moviesViewModel: MovieViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun bindDetailViewModel(detailViewModel: DetailViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(SearchDetailViewModel::class)
    abstract fun bindSearchViewModel(searchDetailViewModel: SearchDetailViewModel): ViewModel



    @Binds
    @IntoMap
    @ViewModelKey(FavouriteViewModel::class)
    abstract fun bindFavouriteViewModel(favouriteViewModel: FavouriteViewModel): ViewModel




}
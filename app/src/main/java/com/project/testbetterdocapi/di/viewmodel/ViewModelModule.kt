package com.project.testbetterdocapi.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.project.neardoc.di.viewmodel.ViewModelFactory
import com.project.testbetterdocapi.di.scopes.ViewModelKey
import com.project.testbetterdocapi.viewmodels.HomePageViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun provideViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
    @Binds
    @IntoMap
    @ViewModelKey(HomePageViewModel::class)
    internal abstract fun provideHomePageViewModel(homePageViewModel: HomePageViewModel): ViewModel
}
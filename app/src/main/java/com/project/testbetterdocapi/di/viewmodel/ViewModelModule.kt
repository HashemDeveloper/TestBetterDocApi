package com.project.testbetterdocapi.di.viewmodel

import androidx.lifecycle.ViewModelProvider
import com.project.neardoc.di.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun provideViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}
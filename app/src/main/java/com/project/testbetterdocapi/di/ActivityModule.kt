package com.project.testbetterdocapi.di

import com.project.testbetterdocapi.TestBetterDocMainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [FragmentBuilderModule::class])
    abstract fun contributeActivity(): TestBetterDocMainActivity
}
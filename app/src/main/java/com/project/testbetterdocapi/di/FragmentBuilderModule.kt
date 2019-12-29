package com.project.testbetterdocapi.di

import com.project.testbetterdocapi.view.HomePageFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {
    @ContributesAndroidInjector
    abstract fun contributeHomepageFragment(): HomePageFragment
}
package com.project.testbetterdocapi.di

import com.project.testbetterdocapi.TestBetterDocApiApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, ActivityModule::class, ApplicationModule::class])
interface ApplicationComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun buildApplication(app: TestBetterDocApiApp): Builder
        fun build(): ApplicationComponent
    }
    fun inject(app: TestBetterDocApiApp)
}
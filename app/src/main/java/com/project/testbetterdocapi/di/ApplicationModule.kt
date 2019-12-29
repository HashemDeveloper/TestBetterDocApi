package com.project.testbetterdocapi.di

import android.content.Context
import com.project.testbetterdocapi.TestBetterDocApiApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule {
    @Singleton
    @Provides
    fun provideApplicationContext(app: TestBetterDocApiApp): Context {
        return app
    }
}
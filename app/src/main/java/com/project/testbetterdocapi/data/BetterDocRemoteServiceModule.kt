package com.project.testbetterdocapi.data

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
object BetterDocRemoteServiceModule {
    @Singleton
    @Provides
    @JvmStatic
    internal fun providesBetterDocRemoteApi(retrofit: Retrofit): IBetterDocApi {
        return retrofit.create(IBetterDocApi::class.java)
    }
}
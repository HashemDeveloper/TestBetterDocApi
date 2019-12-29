package com.project.testbetterdocapi

import android.app.Activity
import android.app.Application
import com.project.testbetterdocapi.di.ApplicationInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class TestBetterDocApiApp: Application(), HasActivityInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>


    override fun onCreate() {
        super.onCreate()
        ApplicationInjector.init(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return this.dispatchingAndroidInjector
    }
}
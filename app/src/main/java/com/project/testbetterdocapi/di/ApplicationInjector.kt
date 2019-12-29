package com.project.testbetterdocapi.di

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.project.testbetterdocapi.TestBetterDocApiApp
import dagger.android.AndroidInjection
import dagger.android.support.HasSupportFragmentInjector

object ApplicationInjector {
    fun init(app: TestBetterDocApiApp) {
        DaggerApplicationComponent.builder().buildApplication(app).build().inject(app)
        app.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
            override fun onActivityPaused(p0: Activity) {

            }

            override fun onActivityStarted(activity: Activity) {
                handleActivity(activity)
            }

            override fun onActivityDestroyed(p0: Activity) {

            }

            override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {

            }

            override fun onActivityStopped(p0: Activity) {

            }

            override fun onActivityCreated(p0: Activity, p1: Bundle?) {

            }

            override fun onActivityResumed(p0: Activity) {

            }

        })
    }
    private fun handleActivity(activity: Activity) {
        if (activity is HasSupportFragmentInjector) {
            AndroidInjection.inject(activity)
        }
        if (activity is FragmentActivity) {
            activity.supportFragmentManager
                .registerFragmentLifecycleCallbacks(object: FragmentManager.FragmentLifecycleCallbacks() {
                    override fun onFragmentCreated(
                        fm: FragmentManager,
                        f: Fragment,
                        savedInstanceState: Bundle?
                    ) {
//                        if (f is Injectable) {
//                            AndroidSupportInjection.inject(f)
//                        }
                    }
                }, true)
        }
    }
}
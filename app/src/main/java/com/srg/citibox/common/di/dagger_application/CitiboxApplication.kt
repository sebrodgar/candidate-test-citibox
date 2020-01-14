package com.srg.citibox.common.di.dagger_application

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import com.srg.citibox.BuildConfig
import timber.log.Timber

/**
 * Created by Sebastián Rodríguez on 12,January,2020
 */

class CitiboxApplication : Application() {

    private lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.builder().build()
        initializeLeakDetection()
        initializeTimber()

    }

    fun getApplicationComponent() = applicationComponent

    private fun initializeLeakDetection() {
        if (BuildConfig.DEBUG) LeakCanary.install(this)
    }

    private fun initializeTimber() {
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())

    }
}
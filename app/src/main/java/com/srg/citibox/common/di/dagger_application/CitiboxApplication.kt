package com.srg.citibox.common.dependency_injection.dagger_application

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import com.srg.citibox.BuildConfig
import com.srg.citibox.common.data.network.retrofit.ApiSetup

/**
 * Created by Sebastián Rodríguez on 12,January,2020
 */

class CitiboxApplication : Application() {

    companion object {
        val apiSetup = ApiSetup.initialize()
    }

    private lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.builder().build()
        initializeLeakDetection()

    }

    fun getApplicationComponent() = applicationComponent

    private fun initializeLeakDetection() {
        if (BuildConfig.DEBUG) LeakCanary.install(this)
    }
}
package com.srg.citibox.common.di.dagger_application

import android.app.Application
import com.srg.citibox.common.di.dagger_activity.ActivityComponent
import com.srg.citibox.common.di.dagger_activity.ActivityModule
import com.srg.citibox.common.di.viewmodel.ViewModelModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Sebastián Rodríguez on 13,January,2020
 */

@Singleton
@Component(modules = [ApplicationModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun newActivityComponent(activityModule: ActivityModule): ActivityComponent

}
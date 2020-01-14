package com.srg.citibox.common.dependency_injection.dagger_application

import com.srg.citibox.common.dependency_injection.dagger_activity.ActivityComponent
import com.srg.citibox.common.dependency_injection.dagger_activity.ActivityModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Sebastián Rodríguez on 13,January,2020
 */

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun newActivityComponent(activityModule: ActivityModule): ActivityComponent

}
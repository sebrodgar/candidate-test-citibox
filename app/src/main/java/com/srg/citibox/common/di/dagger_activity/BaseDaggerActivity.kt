package com.srg.citibox.common.di.dagger_activity

import androidx.appcompat.app.AppCompatActivity
import com.srg.citibox.post_detail.ui.PostDetailActivity
import com.srg.citibox.post_list.ui.PostListActivity
import com.srg.citibox.common.di.dagger_application.ApplicationComponent
import com.srg.citibox.common.di.dagger_application.CitiboxApplication
import java.lang.RuntimeException

/**
 * Created by Sebastián Rodríguez on 13,January,2020
 */

abstract class BaseDaggerActivity: AppCompatActivity() {

    inline fun <reified T> getInjector(activity:AppCompatActivity):T{
        return when(activity::class){
            PostListActivity::class, PostDetailActivity::class -> getApplicationComponent().newActivityComponent(ActivityModule()) as T

            else -> throw RuntimeException("This controller is not supported for injections " +
                    activity::class.java.canonicalName
            )
        }
    }

    fun getApplicationComponent(): ApplicationComponent {
        return (application as CitiboxApplication).getApplicationComponent()
    }
}
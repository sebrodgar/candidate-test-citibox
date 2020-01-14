package com.srg.citibox.post_list.ui

import android.os.Bundle
import com.srg.citibox.common.di.dagger_activity.ActivityComponent
import com.srg.citibox.common.di.dagger_activity.BaseDaggerActivity

/**
 * Created by Sebastián Rodríguez on 13,January,2020
 */

class PostListActivity: BaseDaggerActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getInjector<ActivityComponent>(this).inject(this)
    }
}
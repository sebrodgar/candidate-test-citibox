package com.srg.citibox.common.dependency_injection.dagger_activity

import com.srg.citibox.post_detail.presentation.PostDetailActivity
import com.srg.citibox.post_list.presentation.PostListActivity
import dagger.Subcomponent

/**
 * Created by Sebastián Rodríguez on 13,January,2020
 */

@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(postListActivity: PostListActivity)
    fun inject(postDetailActivity: PostDetailActivity)

}
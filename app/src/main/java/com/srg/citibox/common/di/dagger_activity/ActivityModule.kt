package com.srg.citibox.common.di.dagger_activity

import com.srg.citibox.post_list.data.repository.PostListDataRepository
import com.srg.citibox.post_list.domain.usecase.GetAllPostList
import dagger.Module
import dagger.Provides

/**
 * Created by Sebastián Rodríguez on 13,January,2020
 */

@Module
class ActivityModule {

    @Provides
    fun providesGetAllPostList(repository: PostListDataRepository): GetAllPostList =
        GetAllPostList(repository)


    
    /*@Provides
    fun providesGet*/

    /*

    Aqui debo poner lasdependencias que tendrán las activities:
    - Como pueden ser el viewModelFactory
    - Los casos de uso

     */
}
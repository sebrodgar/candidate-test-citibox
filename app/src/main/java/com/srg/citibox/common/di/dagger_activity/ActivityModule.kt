package com.srg.citibox.common.di.dagger_activity

import com.srg.citibox.post_detail.data.repository.PostDetailDataRepository
import com.srg.citibox.post_detail.domain.usecase.GetAuthorByPost
import com.srg.citibox.post_detail.domain.usecase.GetNumberOfCommentByPost
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

    @Provides
    fun providesGetAuthorByPost(repository: PostDetailDataRepository): GetAuthorByPost =
        GetAuthorByPost(repository)

    @Provides
    fun providesGetNumberOfCommentByPost(repository: PostDetailDataRepository): GetNumberOfCommentByPost =
        GetNumberOfCommentByPost(repository)

}
package com.srg.citibox.post_list.data.repository

import com.srg.citibox.common.data.model.CitiboxError
import com.srg.citibox.common.data.model.CitiboxResult
import com.srg.citibox.common.data.model.Post
import com.srg.citibox.post_list.data.datasource.CloudPostListDataSource
import com.srg.citibox.post_list.domain.repository.PostListRepository
import javax.inject.Inject

/**
 * Created by Sebastián Rodríguez on 14,January,2020
 */

class PostListDataRepository @Inject constructor(
    private val cloudPostListDataSource: CloudPostListDataSource
) : PostListRepository {

    override suspend fun getAllPosts(onResult: (CitiboxResult<CitiboxError, List<Post>>) -> Unit) {
        cloudPostListDataSource.getAllPosts(onResult)
    }
}
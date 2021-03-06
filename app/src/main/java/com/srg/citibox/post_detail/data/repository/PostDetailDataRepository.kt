package com.srg.citibox.post_detail.data.repository

import com.srg.citibox.common.data.model.CitiboxError
import com.srg.citibox.common.data.model.CitiboxResult
import com.srg.citibox.common.data.model.Post
import com.srg.citibox.common.data.model.User
import com.srg.citibox.post_detail.data.datasource.CloudPostDetailDataSource
import com.srg.citibox.post_detail.domain.repository.PostDetailRepository
import javax.inject.Inject

/**
 * Created by Sebastián Rodríguez on 16,January,2020
 */

class PostDetailDataRepository @Inject constructor(private val cloudPostDetailDataSource: CloudPostDetailDataSource) :
    PostDetailRepository {

    override suspend fun getAuthorByPost(
        userId: Long,
        onResult: (CitiboxResult<CitiboxError, User>) -> Unit
    ) {
        cloudPostDetailDataSource.getAuthorByPost(userId, onResult)
    }

    override suspend fun getNumberOfCommentsByPost(
        postId: Long,
        onResult: (CitiboxResult<CitiboxError, Int>) -> Unit
    ) {
        cloudPostDetailDataSource.getNumberOfCommentsByPost(postId, onResult)
    }
}
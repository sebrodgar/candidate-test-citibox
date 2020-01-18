package com.srg.citibox.post_detail.domain.repository

import com.srg.citibox.common.data.model.CitiboxError
import com.srg.citibox.common.data.model.CitiboxResult
import com.srg.citibox.common.data.model.Post
import com.srg.citibox.common.data.model.User

/**
 * Created by Sebastián Rodríguez on 16,January,2020
 */

interface PostDetailRepository {

    suspend fun getAuthorByPost(
        userId: Long,
        onResult: (CitiboxResult<CitiboxError, User>) -> Unit
    )

    suspend fun getNumberOfCommentsByPost(
        postId: Long,
        onResult: (CitiboxResult<CitiboxError, Int>) -> Unit
    )
}